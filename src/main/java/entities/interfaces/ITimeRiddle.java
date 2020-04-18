/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

/**
 *
 * @author benja
 * @param <R>
 * @param <H>
 */
public interface ITimeRiddle <R, H> extends IRiddle<R,H>{
    
    public void startTimer();
    public void timeOut();
    
}
