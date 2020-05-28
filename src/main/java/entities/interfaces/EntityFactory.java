/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

/**
 *
 * @author sofieamalielandt
 */
public interface EntityFactory {

    public User find(long id);

    public User find(String username);

    public User getUser(String username);

    public void updateUser(User user);


    
}
