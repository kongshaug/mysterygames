/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

import entities.AttemptDTO;
import enums.Status;

/**
 *
 * @author sofieamalielandt
 */
public interface Attempt {

    public Status getStatus();

    public void validateAnswer(String answer);

    public int calcPoints();

    public Riddle riddle();

    public void update(Attempt attempt);
    
    public AttemptDTO toDTO();
    
    public User user();

}
