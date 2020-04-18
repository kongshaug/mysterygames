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
 * @param <O>
 */
public interface IOptRiddle <R,H,O> extends IRiddle<R,H>{
    
    public O options();
    public void attempt();
    
}
