/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.Riddle;
import entities.interfaces.TimeAttempt;
import enums.Status;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author aamandajuhl
 */
@Entity
@Table(name = "TIMEATTEMPT")
@NamedQuery(name = "TimeAttemptImpl.deleteAllRows", query = "DELETE from TimeAttemptImpl")
class TimeAttemptImpl extends AttemptImpl implements Serializable, TimeAttempt {

    @Column(name = "time_start", updatable = false, nullable = false)
    protected LocalDateTime start;

    @Transient
    protected long time;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "riddle_ID", nullable = false, updatable = false)
    protected TimeRiddleImpl riddle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_ID", nullable = false, updatable = false)
    protected UserImpl user;

    TimeAttemptImpl() {
    }

    TimeAttemptImpl(TimeRiddleImpl riddle, UserImpl user) {
        this.start = LocalDateTime.now();
        this.time = 0;
        this.riddle = riddle;
        this.user = user;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public TimeRiddleImpl getRiddle() {
        return riddle;
    }

    public void setRiddle(TimeRiddleImpl riddle) {
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

        this.calcTime();

        if (this.time <= riddle.time()) {

            boolean solved = this.riddle.validate(answer);

            if (solved) {
                int points = this.calcPoints();
                this.user.levelUp(points);
                this.status = Status.SOLVED;
            }
        } else {
            this.status = Status.FAILED;
        }

    }

    @Override
    public int calcPoints() {

        if (this.time <= riddle.time() * 0.3) {
            return riddle.points();
        } else if (this.time <= riddle.time() * 0.6) {
            return riddle.points() / 2;
        }

        return riddle.points() / 4;

    }

    @Override
    public Riddle riddle() {
        return this.riddle;
    }

    @Override
    public void calcTime() {

        LocalDateTime stopTime = LocalDateTime.now();
        Duration duration = Duration.between(start, stopTime);

        this.time = duration.getSeconds();

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.riddle);
        hash = 41 * hash + Objects.hashCode(this.user);
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
        final TimeAttemptImpl other = (TimeAttemptImpl) obj;
        if (!Objects.equals(this.riddle, other.riddle)) {
            return false;
        }
        return (!Objects.equals(this.user, other.user));
    }

}
