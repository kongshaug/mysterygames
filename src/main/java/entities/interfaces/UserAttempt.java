/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

import enums.Status;

/**
 *
 * @author sofieamalielandt
 */
public interface UserAttempt {

    public Status getStatus();

    public int points();

    public Riddle getRiddle();
    
}
