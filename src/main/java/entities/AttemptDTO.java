/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import enums.Status;

/**
 *
 * @author sofieamalielandt
 */
public abstract class AttemptDTO {

    protected UserDTO user;
    protected RiddleDTO riddle;
    protected Status status;
    

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

}
