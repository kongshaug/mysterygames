/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.Attempt;
import entities.interfaces.EntityFactory;
import entities.interfaces.Riddle;
import entities.interfaces.User;
import java.util.List;
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
    public User getUser(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User addUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Riddle getRiddle(int level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Attempt makeAttempt(Riddle riddle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
