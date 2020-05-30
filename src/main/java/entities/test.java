/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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

        UserImpl user = new UserImpl("sveske");
        List<String> options = new ArrayList();
        options.add("The dentist");
        options.add("The nurse");
        options.add("The gartner");

        OptRiddleImpl riddle = new OptRiddleImpl("Bla Bla Bla", "The gartner", "He works in the garden ", 1, options);
        OptAttemptImpl attempt = new OptAttemptImpl(riddle, user);
        
        user.addAttempt(attempt);
//        riddle.addAttempt(attempt);
     

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            
//            em.persist(user);
//            em.getTransaction().commit();
            
            

        } finally {
            em.close();
        }
   
    }

}
