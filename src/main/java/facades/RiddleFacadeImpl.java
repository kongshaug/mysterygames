/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.EntityFactoryImpl;
import entities.interfaces.Attempt;
import entities.interfaces.EntityFactory;
import errorhandling.NotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import facades.interfaces.RiddleFacade;
import java.util.UUID;

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
    public Attempt newAttempt(long id) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Attempt validateAnswer(UUID riddle_id, long id, String answer) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String hint(UUID riddle_id, long id) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startTime(long id, UUID riddle_id) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
