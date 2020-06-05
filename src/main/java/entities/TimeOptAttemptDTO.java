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
class TimeOptAttemptDTO extends AttemptDTO {
    
    TimeOptAttemptDTO(TimeAttemptImpl attempt) {
       this.riddle = attempt.getRiddle().toDTO();
       this.user = attempt.getUser().toDTO();
       this.status = attempt.getStatus();
    }
    
    TimeOptAttemptDTO(OptAttemptImpl attempt) {
       this.riddle = attempt.getRiddle().toDTO();
       this.user = attempt.getUser().toDTO();
       this.status = attempt.getStatus();
    }
    
}
