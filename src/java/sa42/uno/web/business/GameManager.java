/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.web.business;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;

import sa42.uno.model.Game;
import sa42.uno.model.Player;

/**
 *
 * @author BP
 */
@ApplicationScoped
public class GameManager {
    
    public Map<String,Game> games;
    public String id = UUID.randomUUID().toString().substring(0, 8);
  
    public Map<String,Game> browseAvailableGames(String username){
        
        games = new HashMap<>();
        
        games.put(id, new Game(id,"game1"));
        games.get(id).addPlayer(new Player("bob"));
        games.get(id).setStatus(Game.Status.Started);
        games.get(id).distributeCards();
        return games;
    }
    
    public Game getOneGame(String gameId){
        return games.get(id);
    }
    
    
    
    
    
    
}
