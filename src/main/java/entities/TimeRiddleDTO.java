/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author sofieamalielandt
 */
class TimeRiddleDTO extends RiddleDTO {
    
    protected Long time;
    
    TimeRiddleDTO(TimeRiddleImpl riddle) {
        this.id = riddle.Id();
        this.riddle = riddle.getRiddle();
        this.time = riddle.time();
    }
    
    public long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}
