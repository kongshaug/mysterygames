/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.Attempt;
import entities.interfaces.Riddle;
import entities.interfaces.User;
import enums.Status;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author sofieamalielandt
 */
@MappedSuperclass
abstract class AttemptImpl implements Attempt {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    
    @Column(name = "status", updatable = true, nullable = false)
    protected Status status = Status.PENDING;
    
    @ManyToOne(optional=false) 
    @JoinColumn(name="riddle_ID", nullable=false, updatable=false)
    protected Riddle riddle;
   
    @ManyToOne(optional=false)
    @JoinColumn(name="user_ID", nullable=false, updatable=false)
    protected User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Riddle getRiddle() {
        return riddle;
    }

    public void setRiddle(Riddle riddle) {
        this.riddle = riddle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Riddle riddle() {
        return riddle;
    }
    
}
