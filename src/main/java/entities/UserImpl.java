/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.Attempt;
import entities.interfaces.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author aamandajuhl
 */
@Entity
@Table(name = "USERS")
@NamedQuery(name = "UserImpl.deleteAllRows", query = "DELETE from UserImpl")
class UserImpl implements Serializable, User {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private int userLevel;
    private int highScore;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "user")
    private Set<OptAttemptImpl> optAttempts = new HashSet();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "user")
    private Set<TimeAttemptImpl> timeAttempts = new HashSet();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "user")
    private Set<DigestAttemptImpl> digestAttempts = new HashSet();

    @Transient
    private Set<Attempt> attempts;

    UserImpl() {
    }

    UserImpl(String username) {
        this.username = username;
        this.highScore = 0;
        this.userLevel = 1;

        this.attempts = new HashSet<Attempt>() {
            {
                addAll(optAttempts);
                addAll(timeAttempts);
                addAll(digestAttempts);
            }
        };
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public Set<OptAttemptImpl> getOptAttempts() {
        return optAttempts;
    }

    public void setOptAttempts(Set<OptAttemptImpl> attempts) {
        this.optAttempts = attempts;
    }

    public Set<TimeAttemptImpl> getTimeAttempts() {
        return timeAttempts;
    }

    public void setTimeAttempts(Set<TimeAttemptImpl> attempts) {
        this.timeAttempts = attempts;
    }

    public Set<DigestAttemptImpl> getDigestAttempts() {
        return digestAttempts;
    }

    public void setDigestAttempts(Set<DigestAttemptImpl> digestAttempts) {
        this.digestAttempts = digestAttempts;
    }

    @Override
    public void addAttempt(Attempt attempt) {

        if (attempt instanceof OptAttemptImpl) {
            this.optAttempts.add((OptAttemptImpl) attempt);
        } else if (attempt instanceof TimeAttemptImpl) {
            this.timeAttempts.add((TimeAttemptImpl) attempt);
        } else if (attempt instanceof DigestAttemptImpl) {
            this.digestAttempts.add((DigestAttemptImpl) attempt);
        }

    }

    @Override
    public void addPoints(int points) {

        this.highScore += points;
    }

    @Override
    public void removePoint() {
        this.highScore -= 10;
    }

    @Override
    public void levelUp() {
        this.userLevel++;
    }

    @Override
    public int level() {
        return userLevel;
    }

    @Override
    public Attempt getAttempt(UUID id) {

        for (Attempt a : attempts) {
            if (a.riddle().Id().equals(id)) {
                return a;
            }
        }
        return null;

    }

    @Override
    public int highScore() {
        return highScore;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 17 * hash + Objects.hashCode(this.username);
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
        final UserImpl other = (UserImpl) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

}
