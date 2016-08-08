/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.web.business;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.ejb.Stateless;

import sa42.uno.model.Game;

/**
 *
 * @author BP
 */

@Stateless
public class GameManager {
    
    private Map<String,Game> games;
  
    public Map<String,Game> browseAvailableGames(){
        
        games = new HashMap<>();
        String id = UUID.randomUUID().toString().substring(0, 8);
        games.put(id, new Game(id,"game1"));
        return games;
    }
    
    public Game getOneGame(){
        return new Game(UUID.randomUUID().toString().substring(0, 8),"game2");
    }
    
    
    
    
    
}
