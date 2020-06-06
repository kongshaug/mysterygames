/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.Attempt;
import entities.interfaces.User;
import enums.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private Long id;
    private String username;
    private int userLevel;
    private int highScore;
    @Transient
    private int maxLevel = 5;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "user")
    private List<OptAttemptImpl> optAttempts = new ArrayList();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "user")
    private List<TimeAttemptImpl> timeAttempts = new ArrayList();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "user")
    private List<DigestAttemptImpl> digestAttempts = new ArrayList();

    UserImpl() {
    }

    UserImpl(String username) {
        this.username = username;
        this.highScore = 0;
        this.userLevel = 1;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
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

    public List<OptAttemptImpl> getOptAttempts() {
        return optAttempts;
    }

    public void setOptAttempts(List<OptAttemptImpl> optAttempts) {
        this.optAttempts = optAttempts;
    }

    public List<TimeAttemptImpl> getTimeAttempts() {
        return timeAttempts;
    }

    public void setTimeAttempts(List<TimeAttemptImpl> timeAttempts) {
        this.timeAttempts = timeAttempts;
    }

    public List<DigestAttemptImpl> getDigestAttempts() {
        return digestAttempts;
    }

    public void setDigestAttempts(List<DigestAttemptImpl> digestAttempts) {
        this.digestAttempts = digestAttempts;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public List<Attempt> getAttempts() {
        List<Attempt> attempts = new ArrayList<>();
        attempts.addAll(this.digestAttempts);
        attempts.addAll(this.optAttempts);
        attempts.addAll(this.timeAttempts);

        return attempts;
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
    public void levelUp(int points) {
        if (this.userLevel == this.maxLevel) {
            this.addPoints(points);
        } else {
            this.userLevel++;
            this.addPoints(points);
        }
    }

    @Override
    public int level() {
        return userLevel;
    }

    @Override
    public Attempt getAttempt(UUID id) {
        for (Attempt a : this.getAttempts()) {
            if (a.riddle().Id().equals(id) && a.getStatus().equals(Status.PENDING)) {
                return a;
            }
        }
        return null;
    }

    public Attempt getAttempt(Attempt attempt) {
        for (Attempt a : this.getAttempts()) {
            if (a.equals(attempt)) {
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
    public UserDTO toDTO() {
        return new UserDTO(this);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.username, other.username);
    }

}
