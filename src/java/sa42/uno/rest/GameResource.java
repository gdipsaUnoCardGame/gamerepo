/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.rest;

import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import sa42.uno.model.Game;
import sa42.uno.web.business.GameManager;

/**
 *
 * @author BP
 */
@RequestScoped
@Path("/game")
public class GameResource {

    @EJB
    private GameManager mgr;

    @GET
    @Path("/browse")
    @Produces("application/json")
    public Response browse() {

        Game game = mgr.getOneGame();

        return (Response.ok(game.toJson()).build());
    }

    @GET
    @Path("/browsemany")
    @Produces("application/json")
    public Response browsemany() {
        Map<String, Game> games = mgr.browseAvailableGames();

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
