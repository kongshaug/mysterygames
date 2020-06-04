/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author sofieamalielandt
 */
public class RiddleDTO {

    private UUID id;
    private String riddle;
    private long time;
    List<String> options;

    RiddleDTO(DigestRiddleImpl riddle) {
        this.id = riddle.Id();
        this.riddle = riddle.getRiddle();
    }

    RiddleDTO(TimeRiddleImpl riddle) {
        this.id = riddle.Id();
        this.riddle = riddle.getRiddle();
        this.time = riddle.time();
    }
    
    RiddleDTO(OptRiddleImpl riddle) {
        this.id = riddle.Id();
        this.riddle = riddle.getRiddle();
        this.options = riddle.getOptions();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRiddle() {
        return riddle;
    }

    public void setRiddle(String riddle) {
        this.riddle = riddle;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
    
    

}
