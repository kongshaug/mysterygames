/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.DigestAttempt;
import entities.interfaces.Riddle;
import enums.Status;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@NamedQuery(name = "DigestAttemptImpl.deleteAllRows", query = "DELETE from DigestAttemptImpl")
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

    DigestAttemptImpl(DigestRiddleImpl riddle, UserImpl user) {
        this.tries = 3;
        this.riddle = riddle;
        this.user = user;
    }

    @Override
    public void validateAnswer(String answer) {

        if (this.moreTries()) {
            this.newTry();
            boolean solved = this.riddle.validate(answer);

            if (solved) {
                int points = this.calcPoints();
                this.user.addPoints(points);
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
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.riddle);
        hash = 71 * hash + Objects.hashCode(this.user);
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
        if (!Objects.equals(this.riddle, other.riddle)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

}
