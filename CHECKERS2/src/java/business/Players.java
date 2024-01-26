package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class Players {
    
    private static final Players instance = new Players();

    public static synchronized Players instance() {
        return instance;
    }   

    private Players() {

    }
    
    private static final Map<String, Player> players = Collections.synchronizedMap(new HashMap<String, Player>());
    private static final Map<String, Integer> activity = Collections.synchronizedMap(new HashMap<String, Integer>());    
    
    public static Map<String, Integer> getActivity(){
        return activity;
    }
    
    public static Player getPlayer(String secret_identifier){
        if(activity.containsKey(secret_identifier)){
            activity.put(secret_identifier, 0);
        }
        return players.get(secret_identifier);
    }            
    
    public static String add(Player player) {          
        String secret_identifier = null;
        for (Map.Entry<String, Player> other_player : players.entrySet()) { 
            if(other_player.getValue().getUsername().equalsIgnoreCase(player.getUsername())){
                secret_identifier = other_player.getKey();
            }
        }                
        if(secret_identifier == null){
            secret_identifier = UUID.randomUUID().toString();
            players.put(secret_identifier, player);
            activity.put(secret_identifier, 0);
        }
        return secret_identifier;
    }
    
    public static String secret_identifier(String username){       
        for (Map.Entry<String, Player> set : players.entrySet()) {            
            if (set.getValue().getUsername().equalsIgnoreCase(username)) {                
                return set.getKey();
            }
        }
        return null;
    }
    
    public static Player playerByUsername(String username){       
        for (Map.Entry<String, Player> set : players.entrySet()) {            
            if (set.getValue().getUsername().equalsIgnoreCase(username)) {                
                return set.getValue();
            }
        }
        return null;
    }
   
    public static List<String> player_names(){ 
        List<String> player_names = new ArrayList<>();
        for (Map.Entry<String, Player> player : players.entrySet()) { 
            player_names.add(player.getValue().getUsername());
        }
        return player_names;
    }        
    
    public static void logout(String secret_identifier) {                     
       Player player = players.get(secret_identifier);
       Lobby.remove(player.getUsername());
       Games.remove(player.getUsername());
       Messages.remove(player.getUsername());
       activity.remove(secret_identifier); 
       players.remove(secret_identifier);
    }
    
}
