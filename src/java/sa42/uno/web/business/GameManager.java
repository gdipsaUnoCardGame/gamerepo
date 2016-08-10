/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.web.business;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import sa42.uno.model.Game;
import sa42.uno.model.Player;



/**
 *
 * @author BP
 */

@ApplicationScoped
public class GameManager {
    
    private Map<String,Game> games = new HashMap<>();  
    
    public Map<String,Game> browseAvailableGames(String username){
        
        //TODO some filtering by player
        
       return games;
    }
    
    public Optional<Game> getOneGame(String gameId){
        
        return Optional.ofNullable(games.get(gameId));
    }
   
    public Map<String,Game> getAll() {
        return games;
    }
    
    /*
    public JsonObject remove(String gameId) {
        return gameJsonMap.remove(id);
    }

    public void removeAll() {
        gameJsonMap.clear();
    }
    */

    public String create(String title) {
        
        String id = UUID.randomUUID().toString().substring(0, 8);
        Game myGame = new Game(id,title);
       
        games.put(id, myGame);
      
        return id;
    }
    
     public JsonObject start(String id) {
       
        Game myGame = games.get(id);
        myGame.setStatus(Game.Status.Started);
        myGame.distributeCards();
      
        myGame.getStatus();
        
        System.out.println("testing... \n"+myGame.toString());

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        
        Map<String,Player> players = myGame.getPlayers();
         /*       
        for (Player player : players) {
            
            JsonObject jsonPlayer = Json.createObjectBuilder()
                .add("id", "" + player.getHand())
                .add("name", player.getName()).build();
            
            arrayBuilder.add(jsonPlayer);

        }
        
        JsonObject value = Json.createObjectBuilder()
                .add("id", "" + id)
                .add("name", game.getJsonString("name"))
                .add("status", ""+myGame.getStatus())
                .add("players", arrayBuilder).build();
        
        System.out.println("game - "+value.toString());
        
        gameJsonMap.put(id, value);
        */
        
        return myGame.toJson();
    }

    


   
    
    
    
    
}
