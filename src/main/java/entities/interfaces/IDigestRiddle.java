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
 * @param <D>
 * @param <I>
 */
public interface IDigestRiddle <R, H, D, I> extends IRiddle<R, H>{
    
    public D digest(I input);
    public void attempt();
    
}
