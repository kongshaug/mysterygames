/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades.interfaces;

import entities.interfaces.User;
import errorhandling.NotFoundException;
import java.util.List;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author benja
 */
public interface ScoreBoard {

    public List<User> get() throws NotFoundException;

    public User createUser(String username) throws WebApplicationException;

}
