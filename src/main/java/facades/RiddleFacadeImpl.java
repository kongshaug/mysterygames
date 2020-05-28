/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.EntityFactoryImpl;
import entities.interfaces.EntityFactory;
import entities.interfaces.Riddle;
import entities.interfaces.User;
import entities.interfaces.UserAttempt;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import facades.interfaces.RiddleFacade;

/**
 *
 * @author benja
 */
class RiddleFacadeImpl implements RiddleFacade {
    
    private static EntityManagerFactory emf;
    private static RiddleFacadeImpl instance;
     private static EntityFactory FACTORY;

    //Private Constructor to ensure Singleton
    private RiddleFacadeImpl() {
    }

    public static RiddleFacadeImpl getRiddleFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RiddleFacadeImpl();
            FACTORY = EntityFactoryImpl.getFactory(emf);
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Riddle getRandRiddle(int level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Riddle getRiddleByType(int type, int level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void createUserAttempt(User user, Riddle riddle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserAttempt validateAnswer(long riddle_id, long id, String answer) {
     
     User user = FACTORY.find(id);
     UserAttempt attempt = user.getUserAttempt(riddle_id);
     Boolean solved = attempt.getRiddle().validate(answer);
     if(solved){
         user.addPoint();
     }   
     FACTORY.updateUser(user); 
     return attempt;
    }

    @Override
    public String hint(long riddle_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startTimer(long id, long riddle_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
