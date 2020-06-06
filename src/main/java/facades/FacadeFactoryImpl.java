/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import facades.interfaces.ScoreBoard;
import facades.interfaces.RiddleFacade;
import facades.interfaces.FacadeFactory;

/**
 *
 * @author benja
 */
public class FacadeFactoryImpl implements FacadeFactory {
    
    private static FacadeFactoryImpl instance;

    //Private Constructor to ensure Singleton
    private FacadeFactoryImpl() {
    }

    public static FacadeFactoryImpl getFacadeFactory() {
        if (instance == null) {
            instance = new FacadeFactoryImpl();
        }
        return instance;
    }  

    @Override
    public RiddleFacade getRiddleFacade() {
       return RiddleFacadeImpl.getRiddleFacade();
    }

    @Override
    public ScoreBoard getScoreBoard() {
        return ScoreBoardImpl.getScoreBoard();
    }
    
}
