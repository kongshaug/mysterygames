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
class DigestAttemptDTO extends AttemptDTO {
    
     private Integer tries;
     
     DigestAttemptDTO(DigestAttemptImpl attempt) {
       this.riddle = attempt.getRiddle().toDTO();
       this.user = attempt.getUser().toDTO();
       this.status = attempt.getStatus();
       this.tries = attempt.getTries();
    }
    
     public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }
}
