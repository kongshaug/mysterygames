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
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="user")
    private Set<OptAttemptImpl> optAttempts = new HashSet();
    
//    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="user")
//    private Set<AttemptImpl> attempts = new HashSet();
//    
//    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="user")
//    private Set<AttemptImpl> attempts = new HashSet();
//    @Transient
//    private Set<Attempt> attempts = new HashSet();

    public UserImpl() {
    }

    UserImpl(String username) {
        this.username = username;
        this.highScore = 0;
        this.userLevel = 1;
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
    
    @Override
    public void addAttempt(Attempt attempt) {
        
        if(attempt instanceof OptAttemptImpl){
            this.optAttempts.add((OptAttemptImpl) attempt);
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

        Attempt attempt = null;

//        for (Attempt a : attempts) {
//            if (a.riddle().Id().equals(id)) {
//                attempt = a;
//            }
//        }
        return attempt;
            
    }

    @Override
    public int highScore() {
        return highScore;
    }

}
