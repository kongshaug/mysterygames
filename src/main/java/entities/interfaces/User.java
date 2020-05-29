/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

import java.util.UUID;

/**
 *
 * @author sofieamalielandt
 */
public interface User {
    
    public void addPoints(int points);
    public void removePoint();
    public void levelUp();
    public int level();
    public Attempt getAttempt(UUID id);
    public void addAttempt(Attempt attempt);
    public Attempt getLatestAttempt();

    public int points();

    
}
