/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.TimeRiddle;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author sofieamalielandt
 */
@Entity
@Table(name = "TIMERIDDLE")
@NamedQuery(name = "TimeRiddleImpl.deleteAllRows", query = "DELETE from TimeRiddleImpl")
class TimeRiddleImpl extends RiddleImpl implements Serializable, TimeRiddle {

    @Column(name = "riddle_time", updatable = false, nullable = false)
    protected long riddleTime;

    TimeRiddleImpl() {
    }

    TimeRiddleImpl(String riddle, String answer, String hint, int riddleLevel, long time) {
        this.uid = UUID.randomUUID();
        this.riddle = riddle;
        this.answer = answer;
        this.hint = hint;
        this.riddleLevel = riddleLevel;
        this.riddleTime = time;
    }

    public long getRiddleTime() {
        return riddleTime;
    }

    public void setRiddleTime(long riddleTime) {
        this.riddleTime = riddleTime;
    }

    @Override
    public long time() {
        return riddleTime;
    }
    
    

    
    
        
        
        
    
    
    
    
    
    
    
}
