/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.rest;

import java.util.Map;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sa42.uno.model.Game;
import sa42.uno.model.Player;
import sa42.uno.web.business.GameManager;

/**
 *
 * @author BP
 */
@RequestScoped
@Path("/games")
public class GameResource {
    
    @Inject
    private GameManager mgr;

    @GET
    @Path("join/{gid}")
    public Response joinGame(@PathParam("gid")String gameId,
            @QueryParam("username")String username) {

        
        Optional<Game> opt = mgr.getOneGame(gameId);
        if (!opt.isPresent()){
            return (Response.status(Response.Status.NOT_FOUND).build());
        }
	
        Game game = opt.get();
        
        Player p = new Player(username);
        game.addPlayer(p);
        return (Response.ok().build());
    }

    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Response browsemany(@QueryParam("username")String name) {
        Map<String, Game> games = mgr.browseAvailableGames(name);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        games.values().stream()
                .map(g -> {
                    return (g.toJson());
                }).forEach(j -> {
            arrBuilder.add(j);
        });

        return (Response.ok(arrBuilder.build()).header(
                "Access-Control-Allow-Origin","http://localhost:63342").build());
    }
}
