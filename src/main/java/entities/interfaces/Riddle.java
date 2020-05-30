/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

import java.util.UUID;

/**
 *
 * @author benja
 */
public interface Riddle {
   
    public String hint();
    public int level();
    public int points();   
    public boolean validate(String answer);
    public UUID Id();
}
