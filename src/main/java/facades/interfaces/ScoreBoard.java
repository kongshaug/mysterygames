/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades.interfaces;

import dto.UserDTO;
import entities.interfaces.User;
import errorhandling.NotFoundException;
import java.util.List;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author benja
 */
public interface ScoreBoard {
    
    public List<User> getAllScores() throws NotFoundException;
    
    public User getUser(long id) throws NotFoundException;

    public User createUser(String username) throws WebApplicationException;

    public void removePoint(long id) throws NotFoundException;

    public void addPoints(long id, int points) throws NotFoundException;;
    
}
