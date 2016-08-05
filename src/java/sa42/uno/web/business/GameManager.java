/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.web.business;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import sa42.uno.model.Game;

/**
 *
 * @author BP
 */
public class GameManager {
    
    private Map<String,Game> games;
    
    private void addSomeGames(){
        games = new HashMap<>();
        String id = UUID.randomUUID().toString().substring(0, 8);
        games.put(id, new Game(id,"game1"));
    }
    
    public Map<String,Game> browseAvailableGames(){
        
        addSomeGames();
        return games;
    }
    
    
}
