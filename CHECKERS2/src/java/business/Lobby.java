package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class Lobby {
    
    private static final Lobby instance = new Lobby();

    public static synchronized Lobby instance() {
        return instance;
    }

    private Lobby() {        
        Vencivel.instance();
        CronTasks.instance();
    }
            
    private static final Map<String, HashSet<String>> challenges = Collections.synchronizedMap(new HashMap<String, HashSet<String>>());  
    
    public static Set<String> lobby_players(){
        return challenges.keySet();
    }
                    
    public void process(String secret_identifier, String action, String target) {
        Player player = Players.getPlayer(secret_identifier);
        
        if(action.equalsIgnoreCase("Desafiar") || action.equalsIgnoreCase("Aceitar")){               
            if(challenges.get(target).contains(player.getUsername())){
                challenges.get(target).clear();
                challenges.get(target).add(player.getUsername());
                challenges.get(player.getUsername()).clear();                
            }
            challenges.get(player.getUsername()).add(target);                 
            if(challenges.get(player.getUsername()).contains(target) && challenges.get(target).contains(player.getUsername())){
                Player target_player = Players.playerByUsername(target);
                Player player1 = Math.random() < 0.5 ? player : target_player;
                Player player2 = player1.getId().equalsIgnoreCase(target_player.getId()) ? player : target_player;
                Games.new_game(player1, player2);                
            }            
        } else if(action.equalsIgnoreCase("Cancelar")){
            challenges.get(player.getUsername()).remove(target);
        } else if(action.equalsIgnoreCase("Desistir")){
            challenges.get(player.getUsername()).clear();
        } else if(action.equalsIgnoreCase("Assistir")){
            String game_identifier = Games.game_identifier(Players.playerByUsername(target));
            Games.view(game_identifier, player);             
        } 
    }
    
    public static void add(String player_name){         
        if(challenges.get(player_name) != null){
            for(String challenged : challenges.get(player_name)){ 
                if(challenges.get(challenged).contains(player_name)){
                    challenges.get(challenged).remove(player_name);
                }
            }
        }
        Games.remove(player_name);
        challenges.put(player_name, new HashSet<>());        
    }
    
    public static void remove(String player_name){
        challenges.remove(player_name);
        for (Map.Entry<String, HashSet<String>> challenge : challenges.entrySet()) {
            challenge.getValue().remove(player_name);
        } 
    }
        
    public String process(String secret_identifier) { 
        Player player = Players.getPlayer(secret_identifier);
        if(player == null){
            return new String();
        }          
        return attributesValues(Players.getPlayer(secret_identifier));
    }        
    
    private String attributesValues(Player player){             
        List<String> other_players = new ArrayList<>();
        List<String> contact = new ArrayList<>(player.getContact());
        List<String> block = new ArrayList<>(player.getBlock());
        List<String> challenger = new ArrayList<>();
        List<String> challenged = new ArrayList<>();             
                      
        for (String player_username : Players.player_names()) {            
            if(player_username.equalsIgnoreCase(player.getUsername())){               
                challenged.addAll(challenges.get(player.getUsername()));                
            } else {
                other_players.add(player_username);   
                if(challenges.get(player_username).contains(player.getUsername())){
                    challenger.add(player_username);                                        
                }                                              
            }                                                                                                                                           
        }      
                
        StringBuilder attributeValues = new StringBuilder();
        attributeValues.append("other_players").append("=").append(other_players.toString());
        attributeValues.append("contact").append("=").append(contact.toString());
        attributeValues.append("block").append("=").append(block.toString());
        attributeValues.append("playing").append("=").append(Games.playing().toString());
        attributeValues.append("challenger").append("=").append(challenger.toString());
        attributeValues.append("challenged").append("=").append(challenged.toString());                                                   
        attributeValues.append(Messages.pull(player.getUsername()));
        
        return attributeValues.toString();
    }                
    
}
