/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

/**
 *
 * @author sofieamalielandt
 * @param <R>
 */
public interface DigestRiddle<R> extends Riddle {
    
    public R digest(String input);
    
    
}
