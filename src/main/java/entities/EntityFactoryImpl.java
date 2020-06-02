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
    private static List<RiddleImpl> riddles;

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
        User user;

        try {
            user = em.find(UserImpl.class, username);

        } finally {
            em.close();
        }
        return user;

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
    public User updateUser(User newUser, Attempt newAttempt) throws NotFoundException {

        EntityManager em = getEntityManager();

        UserImpl user;

        try {

            user = em.find(UserImpl.class, newUser.getId());

            if (user == null) {
                throw new NotFoundException("User not found");
            }

            user.setHighScore(newUser.highScore());
            user.setUserLevel(newUser.level());

            Attempt attempt = user.getAttempt(newAttempt.riddle().Id());
            attempt.update(newAttempt);

            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return user;

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

    private RiddleImpl getRiddle(UserImpl user) {

        List<RiddleImpl> levelRiddles = new ArrayList();

        riddles.stream().filter((riddle) -> (riddle.level() == user.level())).forEach((riddle) -> {
            levelRiddles.add(riddle);
        });

        Random random = new Random();
        RiddleImpl newRiddle = null;
        int list_length = levelRiddles.size();

        for (int i = 0; i < list_length; i++) {
            int index = random.nextInt(levelRiddles.size());
            newRiddle = levelRiddles.get(index);

            if (user.riddleTried(newRiddle.Id())) {
                levelRiddles.remove(index);
            } else {
                return newRiddle;
            }
        }

        return newRiddle;
    }

    private List<RiddleImpl> getAllRiddles() {

        EntityManager em = getEntityManager();

        List<RiddleImpl> allRiddles = new ArrayList();

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
        AttemptImpl attempt;
        RiddleImpl riddle;

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
        for (RiddleImpl riddle : riddles) {

            if (riddle.Id().equals(riddle_id)) {
                return (DigestRiddleImpl) riddle;
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

}
