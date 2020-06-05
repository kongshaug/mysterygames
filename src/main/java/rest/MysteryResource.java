package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import entities.AttemptDTO;
import entities.UserDTO;
import entities.interfaces.Attempt;
import entities.interfaces.User;
import errorhandling.NotFoundException;
import facades.FacadeFactoryImpl;
import java.util.List;
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
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("mystery")
public class MysteryResource {

    private static final FacadeFactory FACTORY = FacadeFactoryImpl.getFacadeFactory();
    private static final RiddleFacade FACADE = FACTORY.getRiddleFacade();
    private static final ScoreBoard SCOREBOARD = FACTORY.getScoreBoard();
    private static final JsonParser JP = new JsonParser();
     private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Riddly Hello World\"}";
    }

    // Takes a user id and generates an UserAttempt in the database - returns a random riddle
    // corresponding to the user's level
    @POST
    @Path("/riddle/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getRiddle(@PathParam("id") long id) {
        try {

            Attempt attempt = FACADE.newAttempt(id);
            AttemptDTO a = attempt.toDTO();
            return gson.toJson(a);

        } catch (NotFoundException e) {

            throw new WebApplicationException(e.getMessage(), 400);
        }
    }

//    Takes a riddle id and an user id, gets Attempt from the database, validate answer and 
//    uppdates Attempt corresponding to answer (SOLVED or FAILED).
//    Returns the RiddleAttempt
    @PUT
    @Path("/riddle/{riddle_id}/{user_id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String answer(@PathParam("riddle_id") UUID riddle_id, @PathParam("user_id") long id, String answer) {
        try {
            
            answer = JP.parse(answer).getAsJsonObject().get("answer").getAsString();
            Attempt attempt = FACADE.validateAnswer(riddle_id, id, answer);
            return gson.toJson(attempt.toDTO());

        } catch (NotFoundException | WebApplicationException e) {

            throw new WebApplicationException(e.getMessage(), 400);
        }

    }

//    Takes an username, creates an user in the database - returns the User
    @POST
    @Path("/user")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String createUser(String username) {
        try {
            
            username = JP.parse(username).getAsJsonObject().get("username").getAsString();
            User user = SCOREBOARD.createUser(username);
            return gson.toJson(user.toDTO());

        } catch (WebApplicationException e) {

            throw new WebApplicationException(e.getMessage(), 400);
        }
    }

//    Takes a riddle_id and returns the hint of the riddle
    @GET
    @Path("/hint/{user_id}/{riddle_id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHint(@PathParam("user_id") long id, @PathParam("riddle_id") UUID riddle_id) {
        try {

            return FACADE.hint(riddle_id, id);

        } catch (NotFoundException | WebApplicationException e) {

            throw new WebApplicationException(e.getMessage(), 400);
        }

    }

    //Returns all users in the database
    @GET
    @Path("/scoreboard")
    @Produces({MediaType.APPLICATION_JSON})
    public List<UserDTO> getScoreBoard() {
        try {
            List<User> users = SCOREBOARD.get();
            List<UserDTO> usersDTO = users.stream().sorted(Comparator.comparing(User::highScore).reversed()).map(user -> user.toDTO()).collect(Collectors.toList());
            return usersDTO;

        } catch (NotFoundException | WebApplicationException e) {
            throw new WebApplicationException(e.getMessage(), 400);
        }
    }

//  Return digest of a riddle
    @GET
    @Path("/digest/{riddle_id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String digest(@PathParam("riddle_id") UUID riddle_id, String input) {
        try {
            input = JP.parse(input).getAsJsonObject().get("input").getAsString();
            return FACADE.digestInput(riddle_id, input);

        } catch (NotFoundException | WebApplicationException e) {

            throw new WebApplicationException(e.getMessage(), 400);
        }
    }
    
    @POST
    @Path("/riddles/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
            FACADE.populate();
            return "The way you populate me...";
 
    }

}
