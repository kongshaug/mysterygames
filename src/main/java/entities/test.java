/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author sofieamalielandt
 */
public class test {

    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        EntityManager em = emf.createEntityManager();

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
        options2.add("2");
        options2.add("3");
        options2.add("6");

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
                "Military time", "", 4, 60);

        TimeRiddleImpl r9 = new TimeRiddleImpl("How many likes did Jack’s photo get, if only one statmant is true.\n"
                + "1. Jacks’s photo got at least 85 likes\n"
                + "2. Jacks’s phoito got fever than 85 likes\n"
                + "3. Jack’s photo got at least 1 like",
                "0 likes", "I Dont think Jack has that many friends", 3, 90);

        DigestRiddleImpl r10 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "2 * x + 2", "Try to -2 from the response", 2, "2 * x + 2");

        DigestRiddleImpl r11 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "x > 12", "Lower than?", 1, "x > 12");

        DigestRiddleImpl r12 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "x % x", "Modulus", 3, "x % x");

        DigestRiddleImpl r13 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "x % 11", "Modulus", 3, "x % 11");

        DigestRiddleImpl r14 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "x.length() + 3", "Length of?", 4, "x.length() + 3");

        DigestRiddleImpl r15 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "x.charAt(0) * x.length()", "The first element in the input is most important", 5, "x.charAt(0) * x.length()");

        DigestRiddleImpl r16 = new DigestRiddleImpl("Enter a number and figure out the function based on the response. "
                + "You can try as many times as you like, but you only have 3 attempts to guess the function.",
                "365 / x", "Think of how many days there is in a year", 4, "350 / x");

//        user.addAttempt(attempt);
////        riddle.addAttempt(attempt);
//     
//
//        try {
//            em.getTransaction().begin();
//            em.persist(user);
//            em.getTransaction().commit();
//            
////            em.persist(user);
////            em.getTransaction().commit();
//            
//            
//
//        } finally {
//            em.close();
//        }
    }

}
