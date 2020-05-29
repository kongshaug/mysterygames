/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.EntityFactoryImpl;
import entities.interfaces.EntityFactory;
import errorhandling.NotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import facades.interfaces.ScoreBoard;
import javax.ws.rs.WebApplicationException;
import entities.interfaces.User;

/**
 *
 * @author benja
 */
class ScoreBoardImpl implements ScoreBoard {

    private static ScoreBoardImpl instance;
    private static EntityFactory FACTORY;

    //Private Constructor to ensure Singleton
    private ScoreBoardImpl() {
    }

    public static ScoreBoardImpl getScoreBoard() {
        if (instance == null) {
            instance = new ScoreBoardImpl();
            FACTORY = EntityFactoryImpl.getFactory();
        }
        return instance;
    }

    @Override
    public List<User> get() throws NotFoundException {

        List<User> users = FACTORY.getAllUsers();
        return users;

    }

    @Override
    public User createUser(String username) throws WebApplicationException {

        User user = FACTORY.getUser(username);

        if (user != null) {
            throw new WebApplicationException("Username is already in use", 400);
        }
        return FACTORY.addUser(username);

    }

}
