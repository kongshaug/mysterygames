/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author sofieamalielandt
 */
public interface EntityFactory {

    public User getUser(long id);
    public User getUser(String username);
    public User addUser(String username);
    public User updateUser(User user);
    public List<User> getAllUsers();
    public Riddle getRiddle(int level);
    public Attempt makeAttempt(Riddle riddle);
    
    
    
    
    
    


    
}
