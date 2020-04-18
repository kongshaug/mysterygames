/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

/**
 *
 * @author benja
 */
public interface IRiddleFactory {
    
    public <R, H> IRiddle<R, H> getRiddle();
    public <R, H, O> IOptRiddle<R, H, O> getOptRiddle();
    public <R, H, D, I> IDigestRiddle<R, H, D, I> getDigestRiddle();
    public <R, H> ITimeRiddle<R, H> getTimeRiddle();
    
    
}
