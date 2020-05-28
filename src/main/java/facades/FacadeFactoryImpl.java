/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import facades.interfaces.ScoreBoard;
import facades.interfaces.RiddleFacade;
import facades.interfaces.FacadeFactory;

/**
 *
 * @author benja
 */
public class FacadeFactoryImpl implements FacadeFactory {
    
    private static EntityManagerFactory emf;
    private static FacadeFactoryImpl instance;

    //Private Constructor to ensure Singleton
    private FacadeFactoryImpl() {
    }

    public static FacadeFactoryImpl getFacadeFactory(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeFactoryImpl();
        }
        return instance;
    }  

    @Override
    public RiddleFacade getRiddleFacade() {
       return RiddleFacadeImpl.getRiddleFacade(emf);
    }

    @Override
    public ScoreBoard getScoreBoard() {
        return ScoreBoardImpl.getScoreBoard(emf);
    }
    
}
