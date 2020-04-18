/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.interfaces.IScore;
import facades.interfaces.IScoreBoard;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author benja
 */
class ScoreBoard implements IScoreBoard {

    private static EntityManagerFactory emf;
    private static ScoreBoard instance;

    //Private Constructor to ensure Singleton
    private ScoreBoard() {
    }

    public static ScoreBoard getScoreBoard(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ScoreBoard();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<IScore> getAllScores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setScore(String name, int score, int numOfRiddles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IScore getScore(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
