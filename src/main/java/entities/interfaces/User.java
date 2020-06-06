/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

import entities.UserDTO;
import java.util.UUID;

/**
 *
 * @author sofieamalielandt
 */
public interface User {

    public void addPoints(int points);

    public void removePoint();

    public void levelUp(int points);

    public int level();

    public Attempt getAttempt(UUID id);

    public void addAttempt(Attempt attempt);

    public int highScore();

    public Long getId();

    public String getUsername();

    public UserDTO toDTO();

}
