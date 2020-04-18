/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.IDigestRiddle;
import entities.interfaces.IOptRiddle;
import entities.interfaces.IRiddle;
import entities.interfaces.IRiddleFactory;
import entities.interfaces.ITimeRiddle;

/**
 *
 * @author benja
 */
public class RiddleFactory implements IRiddleFactory{
    
    private static RiddleFactory instance = null;

    public static RiddleFactory getInstance()
    {
        if(instance == null)
        {
            instance = new RiddleFactory();
        }
        return instance;
    }
private RiddleFactory() {}


    @Override
    public <R, H> IRiddle<R, H> getRiddle() {
        return new Riddle<>();
    }

    @Override
    public <R, H, O> IOptRiddle<R, H, O> getOptRiddle() {
       return new OptRiddle<>();
    }

    @Override
    public <R, H, D, I> IDigestRiddle<R, H, D, I> getDigestRiddle() {
        return new DigestRiddle<>();
    }

    @Override
    public <R, H> ITimeRiddle<R, H> getTimeRiddle() {
       return new TimeRiddle<>();
    }
    
}
