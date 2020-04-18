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
public interface IRiddle <R, H> {
    
    public R get();
    public boolean validate();
    public H hint();
    public int level();
    public int point();
    
    
}
