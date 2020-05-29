/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades.interfaces;

import entities.interfaces.Attempt;
import errorhandling.NotFoundException;
import java.util.UUID;

/**
 *
 * @author benja
 */
public interface RiddleFacade {
    
    //Takes an user id, and get a random riddle and generates an Attempt and adds it to the user
    public Attempt newAttempt(long user_id) throws NotFoundException;

    public Attempt validateAnswer(UUID riddle_id, long user_id, String answer) throws NotFoundException;

    public String hint(UUID riddle_id, long user_id) throws NotFoundException;

    public void startTime(long user_id, UUID riddle_id) throws NotFoundException;


    
}