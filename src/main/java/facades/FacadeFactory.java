/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import facades.interfaces.IFacadeFactory;
import facades.interfaces.IRiddleFacade;
import facades.interfaces.IScoreBoard;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author benja
 */
public class FacadeFactory implements IFacadeFactory {
    
      private static EntityManagerFactory emf;
    private static FacadeFactory instance;

    //Private Constructor to ensure Singleton
    private FacadeFactory() {
    }

    public static FacadeFactory getFacadeFactory(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeFactory();
        }
        return instance;
    }  

    @Override
    public IRiddleFacade getRiddleFacade() {
       return RiddleFacade.getRiddleFacade(emf);
    }

    @Override
    public IScoreBoard getScoreBoard() {
        return ScoreBoard.getScoreBoard(emf);
    }
    
}
