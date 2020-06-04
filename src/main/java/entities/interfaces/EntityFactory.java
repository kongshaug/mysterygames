/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

import errorhandling.NotFoundException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author sofieamalielandt
 */
public interface EntityFactory {

    public User getUser(long id) throws NotFoundException;

    public User getUser(String username);

    public User addUser(String username);

    public User updateUser(User newUser, Attempt newAttempt) throws NotFoundException;

    public List<User> getAllUsers() throws NotFoundException;

//    public Riddle getRiddle(int level) throws NotFoundException;

    public Attempt makeAttempt(long id) throws NotFoundException;

    public DigestRiddle getDigestRiddle(UUID riddle_id) throws NotFoundException;

    public void updateUser(User newUser) throws NotFoundException;
    
    public boolean userTriedRiddle(UUID id, Set<Attempt> attempts);

}
