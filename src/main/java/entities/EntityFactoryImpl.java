/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.EntityFactory;
import entities.interfaces.User;
import entities.interfaces.UserAttempt;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author benja
 */
public class EntityFactoryImpl implements EntityFactory {

    private static EntityFactoryImpl instance = null;
    private static EntityManagerFactory emf;

    public static EntityFactoryImpl getFactory(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EntityFactoryImpl();
        }
        return instance;
    }
    
    private EntityFactoryImpl() {
    }

    @Override
    public User find(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User find(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

}
