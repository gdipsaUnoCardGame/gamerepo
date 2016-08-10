/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.rest;


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
    
@Path("/games")
@Consumes("application/json")
@Produces("application/json")

    @GET
    public JsonArray getAll() {
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (JsonObject game : GameManagerr.getAll()) {
            arrayBuilder.add(game);
        }
        return arrayBuilder.build();
    }

    @GET
    @Path("{id}")
    public JsonObject get(@PathParam("id") int id) {
        return GameManagerr.get(id);
    }

    @DELETE
    @Path("{id}")
    public JsonObject remove(@PathParam("id") int id) {
        return GameManagerr.remove(id);
    }

    @DELETE
    public void removeAll() {
        GameManagerr.removeAll();
    }

    @POST
    public JsonArray create(JsonObject game) {
        return Json.createArrayBuilder().add(GameManagerr.create(game)).build();
    }
    
    @POST
    @Path("{id}")
    public JsonObject start(@PathParam("id") int id) {
        JsonObject json = GameManagerr.start(id);
        return json;
    }
}

