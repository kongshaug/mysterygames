/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades.interfaces;

import dto.RiddleDTO;

import entities.interfaces.Riddle;
import entities.interfaces.User;
import entities.interfaces.UserAttempt;

/**
 *
 * @author benja
 */
public interface RiddleFacade {
    
    public Riddle getRandRiddle(int level);
    
    public Riddle getRiddleByType(int type, int level); // Figure out how to identify what type of puzzle

    public void createUserAttempt(User user, Riddle riddle);

    public UserAttempt validateAnswer(long riddle_id, long id, String answer);

    public String hint(long riddle_id);

    public void startTimer(long id, long riddle_id);


    
}