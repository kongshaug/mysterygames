/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

import java.time.LocalDateTime;

/**
 *
 * @author sofieamalielandt
 */
public interface TimeAttempt extends Attempt {
    
    public long getTime(LocalDateTime stop);
    
}
