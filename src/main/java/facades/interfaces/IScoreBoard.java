/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades.interfaces;

import entities.interfaces.IScore;
import java.util.List;

/**
 *
 * @author benja
 */
public interface IScoreBoard {
    
    public List<IScore> getAllScores();
    
    public void setScore(String name, int score, int numOfRiddles);

    /**
     *
     * @param name
     * @return
     */
    public IScore getScore(String name);
    
}
