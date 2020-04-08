package rest;

import domain.Game;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import service.GameService;
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("/games")
public class GameResourceRESTful {
    
    @Inject
    GameService gameService;

    @POST
    public Response createGame(Game newGame) {
       
        return Response.ok( gameService.createGame(newGame)).build();
    }
    

    @DELETE
    @Path("{id}")
    public Response deleteGame(@PathParam("id")int id) {
        Game game = gameService.removeGame(id);
        if(game == null){
        	return Response.noContent().build();
        }
        return Response.ok(game).build();
    }

    @PUT
    public Response updateGame(Game game) {
        return Response.ok(gameService.updateGame(game)).build();
    }

    @GET
    @Path("{id}")
    public Response findGameByID(@PathParam("id")int id) {
        Game game = gameService.findGameByID(id);
        if(game == null){
        	return Response.noContent().build();
        }
        return Response.ok(game).build();
    }

    @GET
    public Response getAllGames() {
        List<Game> games = gameService.getAllGames();
        if (!games.isEmpty()) {
            return Response.ok(games).build();
        }else{
            return Response.noContent().build();
        }
         
    }

}
