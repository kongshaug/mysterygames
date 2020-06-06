/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.Riddle;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author sofieamalielandt
 */
@MappedSuperclass
abstract class RiddleImpl implements Riddle {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @Column(name = "uid", updatable = false, nullable = false)
    protected UUID uid;
 
    @Column(name = "riddle", updatable = false, nullable = false)
    protected String riddle;
    
    @Column(name = "answer", updatable = false, nullable = false)
    protected String answer;
    
    @Column(name = "hint", updatable = false, nullable = false)
    protected String hint;
    
    @Column(name = "riddle_level", updatable = false, nullable = false)
    protected int riddleLevel;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRiddle() {
        return riddle;
    }

    public void setRiddle(String riddle) {
        this.riddle = riddle;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getRiddleLevel() {
        return riddleLevel;
    }

    public void setRiddleLevel(int riddleLevel) {
        this.riddleLevel = riddleLevel;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    @Override
    public String hint() {
        return this.hint;
    }

    @Override
    public int level() {
        return riddleLevel;
    }

    @Override
    public int points() {
        return riddleLevel * 10;
    }

    @Override
    public boolean validate(String answer) {

        return answer.toLowerCase().equals(this.answer.toLowerCase());
    }
    
    @Override
    public UUID Id() {
        return uid;
    }  
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.uid);
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
        final RiddleImpl other = (RiddleImpl) obj;
        return Objects.equals(this.uid, other.uid);
    }
}
