/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.Attempt;
import entities.interfaces.DigestAttempt;
import entities.interfaces.Riddle;
import entities.interfaces.User;
import enums.Status;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;




/**
 *
 * @author aamandajuhl
 */
@Entity
@Table(name = "DIGESTATTEMPT")
class DigestAttemptImpl extends AttemptImpl implements Serializable, DigestAttempt {

    @Column(name = "tries", updatable = true, nullable = false)
    protected int tries;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "riddle_ID", nullable = false, updatable = false)
    protected DigestRiddleImpl riddle;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_ID", nullable = false, updatable = false)
    protected UserImpl user;

    DigestAttemptImpl() {
    }

    DigestAttemptImpl(DigestRiddleImpl riddle, UserImpl user ) {
        this.tries = 3;
        this.riddle = riddle;
        this.user = user;
       
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public DigestRiddleImpl getRiddle() {
        return riddle;
    }

    public void setRiddle(DigestRiddleImpl riddle) {
        this.riddle = riddle;
    }

    public UserImpl getUser() {
        return user;
    }

    public void setUser(UserImpl user) {
        this.user = user;
    }

    @Override
    public void validateAnswer(String answer) {

        if (this.moreTries()) {
            this.newTry();
            boolean solved = this.riddle.validate(answer);

            if (solved) {
                int points = this.calcPoints();
                this.user.levelUp(points);
                this.status = Status.SOLVED;
            } else if (!this.moreTries()) {
                this.status = Status.FAILED;
            }
        } else {
            this.status = Status.FAILED;
        }
    }

    @Override
    public int calcPoints() {

        if (this.tries == 2) {
            return this.riddle.points();
        } else if (this.tries == 1) {
            return this.riddle.points() / 2;
        }

        return this.riddle.points() / 4;

    }

    @Override
    public Riddle riddle() {
        return this.riddle;
    }

    @Override
    public void newTry() {
        this.tries--;
    }

    @Override
    public boolean moreTries() {
        return tries > 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.riddle);
        hash = 31 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DigestAttemptImpl other = (DigestAttemptImpl) obj;
        if(!Objects.equals(this.id, other.id)){
            return false;
        }
        if (!Objects.equals(this.riddle, other.riddle)) {
            return false;
        }
        return Objects.equals(this.user, other.user);
    }

    @Override
    public void update(Attempt attempt) {
        this.setStatus(attempt.getStatus());
        this.newTry();

    }

    @Override
    public AttemptDTO toDTO() {
        return new DigestAttemptDTO(this);
    }

    @Override
    public User user() {
        return this.user;
    }
}
