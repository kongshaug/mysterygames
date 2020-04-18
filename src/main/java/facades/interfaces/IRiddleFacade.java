/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades.interfaces;

import entities.interfaces.IRiddle;

/**
 *
 * @author benja
 */
public interface IRiddleFacade {
    
    public <R, H> IRiddle<R, H> getRandRiddle(int level);
    public <R, H> IRiddle<R, H> getRiddleByType(int type, int level); // Figure out how to identify what type of puzzle
    public String validateAnswer(String answer, int id);
    

}
