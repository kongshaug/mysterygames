/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author sofieamalielandt
 */
class OptRiddleDTO extends RiddleDTO {
    
    private List<String> options;
    
    OptRiddleDTO(OptRiddleImpl riddle) {
        this.id = riddle.Id();
        this.riddle = riddle.getRiddle();
        this.options = riddle.getOptions();
    }
    
    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
    
}
