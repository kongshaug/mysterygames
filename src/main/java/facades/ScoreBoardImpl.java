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

    private static EntityManagerFactory emf;
    private static ScoreBoardImpl instance;
    private static EntityFactory FACTORY;

    //Private Constructor to ensure Singleton
    private ScoreBoardImpl() {
    }

    public static ScoreBoardImpl getScoreBoard(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ScoreBoardImpl();
            FACTORY = EntityFactoryImpl.getFactory(emf);
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public User getUser(long id) throws NotFoundException {

        User user = FACTORY.find(id);

        if (user == null) {
            throw new NotFoundException("No user found");
        }
        return user;
    }

    @Override
    public User createUser(String username) {

        User user = FACTORY.find(username);

        if (user != null) {
            throw new WebApplicationException("Username is already in use");
        }
        return FACTORY.getUser(username);
    }

    @Override
    public void removePoint(long id) throws NotFoundException {

        User user = FACTORY.find(id);

        if (user == null) {
            throw new NotFoundException("No user found");
        }
        user.removePoint();
        FACTORY.updateUser(user);
    }

    @Override
    public List<User> getAllScores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPoints(long id, int points) throws NotFoundException {
        User user = FACTORY.find(id);

        if (user == null) {
            throw new NotFoundException("No user found");
        }
        user.addPoint();
        FACTORY.updateUser(user);}

}
