/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.Attempt;
import enums.Status;

/**
 *
 * @author sofieamalielandt
 */
public class AttemptDTO {

    private UserDTO user;
    private RiddleDTO riddle;
    private Status status;
    private int tries;
      
    AttemptDTO(DigestAttemptImpl attempt) {
       this.riddle = attempt.getRiddle().toDTO();
       this.user = attempt.getUser().toDTO();
       this.status = attempt.getStatus();
       this.tries = attempt.getTries();
    }
    
    AttemptDTO(TimeAttemptImpl attempt) {
       this.riddle = attempt.getRiddle().toDTO();
       this.user = attempt.getUser().toDTO();
       this.status = attempt.getStatus();
    }
    
    AttemptDTO(OptAttemptImpl attempt) {
       this.riddle = attempt.getRiddle().toDTO();
       this.user = attempt.getUser().toDTO();
       this.status = attempt.getStatus();
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RiddleDTO getRiddle() {
        return riddle;
    }

    public void setRiddle(RiddleDTO riddle) {
        this.riddle = riddle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }
    
    
 
}
