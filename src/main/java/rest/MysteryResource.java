package rest;

import dto.PersonDTO;

import utils.EMF_Creator;

import facades.FacadeFactory;
import facades.interfaces.IFacadeFactory;
import facades.interfaces.IRiddleFacade;
import facades.interfaces.IScoreBoard;
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

@Path("mystery")
public class MysteryResource <R> {
    
    

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final IFacadeFactory FACTORY =  FacadeFactory.getFacadeFactory(EMF);
    private static final IRiddleFacade FACADE = FACTORY.getRiddleFacade();
    private static final IScoreBoard SCOREBOARD = FACTORY.getScoreBoard();
    
            
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo()
    {
        return "{\"msg\":\"Riddly Hello World\"}";
    }
    
//    @GET
//    @Path("/riddle")
//    @Produces({MediaType.APPLICATION_JSON})
//    public R getRiddle() {
//       return null;
//    }
//    
//    @GET
//    @Path("/{id}")
//    @Produces({MediaType.APPLICATION_JSON})
//    public void getPerson(@PathParam("id") long id) {
//       
//    }
//    
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public PersonDTO addPerson(PersonDTO person){
//       
//        person.setId(464);
//        return person;
//    }



}
