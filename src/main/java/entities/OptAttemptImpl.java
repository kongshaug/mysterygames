/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.Riddle;
import entities.interfaces.User;
import enums.Status;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author sofieamalielandt
 */
@Entity
@Table(name = "OPTATTEMPT")
@NamedQuery(name = "OptAttemptImpl.deleteAllRows", query = "DELETE from OptAttemptImpl")
class OptAttemptImpl extends AttemptImpl implements Serializable {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "riddle_ID", nullable = false, updatable = false)
    protected OptRiddleImpl riddle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_ID", nullable = false, updatable = false)
    protected UserImpl user;

    OptAttemptImpl() {
    }

    OptAttemptImpl(OptRiddleImpl riddle, UserImpl user) {
        this.user = user;
        this.riddle = riddle;
    }

    public UserImpl getUser() {
        return user;
    }

    public void setUser(UserImpl user) {
        this.user = user;
    }

    public OptRiddleImpl getRiddle() {
        return riddle;
    }

    public void setRiddle(OptRiddleImpl riddle) {
        this.riddle = riddle;
    }

    @Override
    public void validateAnswer(String answer) {

        boolean solved = this.riddle.validate(answer);
        if (solved) {
            int points = calcPoints();
            this.user.addPoints(points);
            this.status = Status.SOLVED;
        } else {
            this.status = Status.FAILED;
        }
    }

    @Override
    public int calcPoints() {
        return this.riddle.level() * 10;
    }

    @Override
    public Riddle riddle() {
        return this.riddle;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final OptAttemptImpl other = (OptAttemptImpl) obj;
        if (!Objects.equals(this.riddle, other.riddle)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }
    

}
