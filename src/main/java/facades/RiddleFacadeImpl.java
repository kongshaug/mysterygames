/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.EntityFactoryImpl;
import entities.interfaces.Attempt;
import entities.interfaces.DigestRiddle;
import entities.interfaces.EntityFactory;
import entities.interfaces.User;
import errorhandling.NotFoundException;
import facades.interfaces.RiddleFacade;
import java.util.UUID;

/**
 *
 * @author benja
 */
class RiddleFacadeImpl implements RiddleFacade {

    private static RiddleFacadeImpl instance;
    private static EntityFactory FACTORY;

    //Private Constructor to ensure Singleton
    private RiddleFacadeImpl() {
    }

    public static RiddleFacadeImpl getRiddleFacade() {
        if (instance == null) {
            instance = new RiddleFacadeImpl();
            FACTORY = EntityFactoryImpl.getFactory();
        }
        return instance;
    }

    @Override
    public Attempt newAttempt(long id) throws NotFoundException {

        Attempt attempt = FACTORY.makeAttempt(id);
        //user = FACTORY.updateUser(user);
        return attempt;

    }

    @Override
    public Attempt validateAnswer(UUID riddle_id, long id, String answer) throws NotFoundException {

        User user = FACTORY.getUser(id);
        Attempt attempt = user.getAttempt(riddle_id);
        attempt.validateAnswer(answer);
        user = FACTORY.updateUser(user, attempt);
        return user.getAttempt(riddle_id);

    }

    @Override
    public String hint(UUID riddle_id, long id) throws NotFoundException {

        User user = FACTORY.getUser(id);
        Attempt attempt = user.getAttempt(riddle_id);
        user.removePoint();
        FACTORY.updateUser(user);
        return attempt.riddle().hint();

    }

    @Override
    public String digestInput(UUID riddle_id, String input) throws NotFoundException {

        DigestRiddle riddle = FACTORY.getDigestRiddle(riddle_id);
        return riddle.digest(input);

    }

}
