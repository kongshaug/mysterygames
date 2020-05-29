package rest;

import dto.AttemptDTO;
import dto.UserDTO;
import entities.interfaces.User;
import errorhandling.NotFoundException;
import utils.EMF_Creator;
import facades.FacadeFactoryImpl;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import facades.interfaces.ScoreBoard;
import facades.interfaces.RiddleFacade;
import facades.interfaces.FacadeFactory;
import java.util.ArrayList;
import java.util.UUID;

@Path("mystery")
public class MysteryResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final FacadeFactory FACTORY = FacadeFactoryImpl.getFacadeFactory(EMF);
    private static final RiddleFacade FACADE = FACTORY.getRiddleFacade();
    private static final ScoreBoard SCOREBOARD = FACTORY.getScoreBoard();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Riddly Hello World\"}";
    }

    // Takes a user id and generates an UserAttempt in the database - returns a random riddle
    // corresponding to the user's level
    @GET
    @Path("/riddle/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AttemptDTO getRiddle(@PathParam("id") long id) {
        try {

            return null;

        } catch (WebApplicationException e) {

            throw new WebApplicationException(e.getMessage(), 400);
        }
    }

    //Takes a riddle id and an user id, gets UserAttempt from the database, validate answer and 
    //uppdates UserAttempt corresponding to answer (SOLVED or FAILED).
    //Returns the RiddleAttempt
    @PUT
    @Path("/riddle/{riddle_id}/{user_id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public AttemptDTO answer(@PathParam("riddle_id") UUID riddle_id, @PathParam("user_id") long id, String answer) {
        try {  
            return null;

        } catch (WebApplicationException e) {

            throw new WebApplicationException(e.getMessage(), 400);
        }

    }

    //Takes an username, creates an user in the database - returns the User
    @POST
    @Path("/user")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public UserDTO createUser(String username) {
        try {
            User user = SCOREBOARD.createUser(username);
            return new UserDTO(user);

        } catch (WebApplicationException e) {

            throw new WebApplicationException(e.getMessage(), 400);
        }
    }

    //Takes a riddle_id and returns the hint of the riddle
    @GET
    @Path("/hint/{user_id}/{riddle_id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHint(@PathParam("user_id") long id, @PathParam("riddle_id") UUID riddle_id) {
        try {

            return null;

        } catch (WebApplicationException e) {

            throw new WebApplicationException(e.getMessage(), 400);
        }

    }

    //Returns all users in the database
    @GET
    @Path("/scoreboard")
    @Produces({MediaType.APPLICATION_JSON})
    public List<UserDTO> getScoreBoard() {
        try {
            return null;

        } catch (WebApplicationException e) {

            throw new WebApplicationException(e.getMessage(), 400);
        }
    }

    //Takes an user id and a riddle id and puts a timestamp in the database.
    @PUT
    @Path("/timer/{id}/{riddle_id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public void startTime(@PathParam("id") long id, @PathParam("riddle_id") UUID riddle_id) {
        try {
      

        } catch (WebApplicationException e) {

            throw new WebApplicationException(e.getMessage(), 400);
        }
    }

//   
//    @POST
//    @Path("/riddle")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public void createRiddle(String riddleAsJSON){
//  
//    }   
}
