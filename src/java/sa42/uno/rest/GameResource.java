/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.rest;

import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    @Path("/{gid}")
    @Produces("application/json")
    public Response join(@PathParam("gid")String gameId,
            @QueryParam("username")String username) {

        Game game = mgr.getOneGame(gameId);
        Player p = new Player(username);
        game.addPlayer(p);
        return (Response.ok(game.toJson()).build());
    }

    @GET 
    @Produces("application/json")
    public Response browsemany(@QueryParam("username")String name) {
        Map<String, Game> games = mgr.browseAvailableGames(name);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        games.values().stream()
                .map(g -> {
                    return (g.toJson());
                }).forEach(j -> {
            arrBuilder.add(j);
        });

        return (Response.ok(arrBuilder.build()).header("Access-Control-Allow-Origin","http://localhost:63342").build());
    }
}
