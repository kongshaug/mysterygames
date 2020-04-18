/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.interfaces.IRiddle;
import facades.interfaces.IRiddleFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author benja
 */
class RiddleFacade implements IRiddleFacade {
    
    private static EntityManagerFactory emf;
    private static RiddleFacade instance;

    //Private Constructor to ensure Singleton
    private RiddleFacade() {
    }

    public static RiddleFacade getRiddleFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RiddleFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    @Override
    public <R, H> IRiddle<R, H> getRandRiddle(int level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <R, H> IRiddle<R, H> getRiddleByType(int type, int level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validateAnswer(String answer, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
