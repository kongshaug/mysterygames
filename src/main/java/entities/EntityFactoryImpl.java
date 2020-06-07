/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.Attempt;
import entities.interfaces.DigestRiddle;
import entities.interfaces.EntityFactory;
import entities.interfaces.Riddle;
import entities.interfaces.User;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author benja
 */
public class EntityFactoryImpl implements EntityFactory {

    private static EntityFactoryImpl instance = null;
    private static EntityManagerFactory emf;
    private static List<Riddle> riddles;

    public static EntityFactoryImpl getFactory() {
        if (instance == null) {
            emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
            instance = new EntityFactoryImpl();
            riddles = instance.getAllRiddles();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private EntityFactoryImpl() {
    }

    @Override
    public User getUser(long id) throws NotFoundException {

        EntityManager em = getEntityManager();
        User user;

        try {

            user = em.find(UserImpl.class, id);
            if (user == null) {
                throw new NotFoundException("No user found");
            }

        } finally {
            em.close();
        }
        return user;
    }

    @Override
    public User getUser(String username) {

        EntityManager em = getEntityManager();
        try {

            TypedQuery<UserImpl> query = em.createQuery("SELECT u FROM UserImpl u WHERE u.username = :username", UserImpl.class);
            List<UserImpl> users = query.setParameter("username", username).getResultList();

            if (users.size() > 0) {
                return users.get(0);
            }

        } finally {
            em.close();
        }

        return null;

    }

    @Override
    public User addUser(String username) {

        EntityManager em = getEntityManager();
        User user;

        try {
            em.getTransaction().begin();

            user = new UserImpl(username);
            em.persist(user);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return user;
    }

    @Override
    public Attempt update(Attempt newAttempt) throws NotFoundException {

        EntityManager em = getEntityManager();

        UserImpl user;
        Attempt attempt;

        try {

            user = em.find(UserImpl.class, newAttempt.user().getId());

            if (user == null) {
                throw new NotFoundException("User not found");
            }

            user.setHighScore(newAttempt.user().highScore());
            user.setUserLevel(newAttempt.user().level());

            attempt = user.getAttempt(newAttempt.riddle().Id());
            attempt.update(newAttempt);

            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return user.getAttempt(attempt);

    }

    @Override
    public List<User> getAllUsers() {

        EntityManager em = getEntityManager();

        List<User> users = new ArrayList();

        TypedQuery<UserImpl> query = em.createQuery("SELECT u FROM UserImpl u", UserImpl.class);
        List<UserImpl> usersImpl = query.getResultList();

        usersImpl.forEach((userImpl) -> {
            users.add(userImpl);
        });

        return users;
    }

    private Riddle getRiddle(UserImpl user) {

        List<Riddle> levelRiddles = new ArrayList();

        riddles.stream().filter((riddle) -> (riddle.level() == user.level())).forEach((riddle) -> {
            levelRiddles.add(riddle);
        });

        Random random = new Random();
        Riddle newRiddle = null;
        int list_length = levelRiddles.size();

        for (int i = 0; i < list_length; i++) {
            int index = random.nextInt(levelRiddles.size());
            newRiddle = levelRiddles.get(index);

            if (userTriedRiddle(newRiddle.Id(), user.getAttempts())) {
                levelRiddles.remove(index);
            } else {
                return newRiddle;
            }
        }

        return newRiddle;
    }

    private List<Riddle> getAllRiddles() {

        EntityManager em = getEntityManager();

        List<Riddle> allRiddles = new ArrayList();

        TypedQuery<TimeRiddleImpl> timeQuery = em.createQuery("SELECT t FROM TimeRiddleImpl t", TimeRiddleImpl.class);
        List<TimeRiddleImpl> timeRiddles = timeQuery.getResultList();

        TypedQuery<OptRiddleImpl> optQuery = em.createQuery("SELECT o FROM OptRiddleImpl o", OptRiddleImpl.class);
        List<OptRiddleImpl> optRiddles = optQuery.getResultList();

        TypedQuery<DigestRiddleImpl> digestQuery = em.createQuery("SELECT d FROM DigestRiddleImpl d", DigestRiddleImpl.class);
        List<DigestRiddleImpl> digestRiddles = digestQuery.getResultList();

        allRiddles.addAll(timeRiddles);
        allRiddles.addAll(optRiddles);
        allRiddles.addAll(digestRiddles);

        return allRiddles;

    }

    @Override
    public Attempt makeAttempt(long id) throws NotFoundException {

        EntityManager em = getEntityManager();

        UserImpl user;
        Attempt attempt;
        Riddle riddle;

        try {
            user = em.find(UserImpl.class, id);
            if (user == null) {
                throw new NotFoundException("No user found");
            }

            riddle = getRiddle(user);
            
            if (riddle instanceof DigestRiddleImpl) {
                attempt = new DigestAttemptImpl((DigestRiddleImpl) riddle, user);
            } else if (riddle instanceof OptRiddleImpl) {
                attempt = new OptAttemptImpl((OptRiddleImpl) riddle, user);
            } else {
                attempt = new TimeAttemptImpl((TimeRiddleImpl) riddle, user);
            }

            user.addAttempt(attempt);

            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();

        } finally {
            em.close();
        }

        return user.getAttempt(attempt.riddle().Id());

    }

    @Override
    public DigestRiddle getDigestRiddle(UUID riddle_id) throws NotFoundException {
        for (Riddle riddle : riddles) {
            if (riddle.Id().equals(riddle_id)) {
                return (DigestRiddle) riddle;
            }
        }

        throw new NotFoundException("Riddle not found");

    }

    @Override
    public void updateUser(User newUser) throws NotFoundException {
        EntityManager em = getEntityManager();
        UserImpl user;

        try {

            user = em.find(UserImpl.class, newUser.getId());

            if (user == null) {
                throw new NotFoundException("User not found");
            }

            user.setHighScore(newUser.highScore());
            user.setUserLevel(newUser.level());

            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    @Override
    public boolean userTriedRiddle(UUID id, List<Attempt> attempts) {

        if (!attempts.isEmpty()) {
            return attempts.stream().anyMatch((attempt) -> attempt.riddle().Id().equals(id));
        }

        return false;
    }

    @Override
    public void populateRiddles() {
        EntityManager em = getEntityManager();

        List<String> options = new ArrayList();
        options.add("Hot and raging fires");
        options.add("Assassins carrying loaded guns");
        options.add("Lions that haven't eaten in 3 years");
        OptRiddleImpl r = new OptRiddleImpl("A murder has been condemned to death, he has to choose between "
                + "3 rooms to face his fate. Which room is safest?", "Lions that haven't eaten in 3 years",
                "Humans can survive without any food for 30-40 days", 1, options);

        List<String> options2 = new ArrayList();
        options2.add("His son");
        options2.add("His father");
        options2.add("Himself");

        OptRiddleImpl r2 = new OptRiddleImpl("A man looks at a painting and a woman comes by and asks: \"Why are you so interested in this painting?\". "
                + "The man replies: \"Brothers and sisters, I have none, but that mans father, "
                + "is my fathers son\". Who is tha man in thew painting?", "His son",
                "My fathers son = himself", 3, options2);

        List<String> options3 = new ArrayList();
        options3.add("2");
        options3.add("3");
        options3.add("6");

        OptRiddleImpl r3 = new OptRiddleImpl("A man is asked what his daughters look like. He answers: "
                + "\"They are all blonde, but two, all brunettes, but two and all redheads but two\". "
                + "How many daughters does the man have?", "3", "Only 1 of the daugthers is blonde", 4, options3);

        TimeRiddleImpl r4 = new TimeRiddleImpl("What have to be broken, before you can use it?", "An egg",
                "Don’t put all of them in one basket", 3, 90);

        TimeRiddleImpl r5 = new TimeRiddleImpl("I’m tall when I’m young, and short when I’m old", "A candle",
                "Lets shine some light on the problem.", 1, 60);

        TimeRiddleImpl r6 = new TimeRiddleImpl("What has many keys, but can’t open a single lock?",
                "A Piano", "It takes years to master the keys on it", 5, 90);

        TimeRiddleImpl r7 = new TimeRiddleImpl("I follow you all the time, and copy your every move, "
                + "but you can’t touch me or catch me. What am I?", "Your shadow",
                "Only Lucky luke is faster then me", 2, 60);

        TimeRiddleImpl r8 = new TimeRiddleImpl("When is 1600 plus 25 and 1700 minus 35 the same thing?",
                "Military time", "What time is it?", 4, 60);

        TimeRiddleImpl r9 = new TimeRiddleImpl("How many likes did Jack’s photo get, if only one statmant is true.\n"
                + "1. Jacks’s photo got at least 85 likes\n"
                + "2. Jacks’s phoito got fever than 85 likes\n"
                + "3. Jack’s photo got at least 1 like",
                "0", "I Dont think Jack has that many friends", 3, 90);

        DigestRiddleImpl r10 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "2*x+2", "Try to -2 from the response", 2, "2 * x + 2");

        DigestRiddleImpl r11 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "x>12", "Lower than?", 1, "x > 12");

        DigestRiddleImpl r12 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "x%x", "Modulus", 3, "x % x");

        DigestRiddleImpl r13 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "x%11", "Modulus", 3, "x % 11");

        DigestRiddleImpl r14 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "x.length()+3", "Length of?", 4, "String.valueOf(x).length() + 3");

        DigestRiddleImpl r15 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "x.charAt(0)*x.length()", "The first element in the input is most important", 5, " Integer.parseInt(String.valueOf(x).substring(0,1)) * String.valueOf(x).length()");

        DigestRiddleImpl r16 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "365/x", "Think of how many days there is in a year", 4, "365 / x");

        List<RiddleImpl> DBriddles = new ArrayList<>();

        DBriddles.add(r);
        DBriddles.add(r2);
        DBriddles.add(r3);
        DBriddles.add(r4);
        DBriddles.add(r5);
        DBriddles.add(r6);
        DBriddles.add(r7);
        DBriddles.add(r8);
        DBriddles.add(r9);
        DBriddles.add(r10);
        DBriddles.add(r11);
        DBriddles.add(r12);
        DBriddles.add(r13);
        DBriddles.add(r14);
        DBriddles.add(r15);
        DBriddles.add(r16);

        try {
            em.getTransaction().begin();
            DBriddles.forEach((riddle) -> {
                em.persist(riddle);
            });
            em.getTransaction().commit();

        } finally {
            em.close();
        }

    }

}
