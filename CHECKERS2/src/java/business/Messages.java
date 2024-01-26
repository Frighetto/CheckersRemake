package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class Messages {
    
    private static final Messages instance = new Messages();

    public static synchronized Messages instance() {
        return instance;
    }

    private Messages() {
    
    }
    
    private static final Map<String, ArrayList<String>> messages = Collections.synchronizedMap(new HashMap<String, ArrayList<String>>());
    
    public static void push(String sender, String receiver, String message) {
        message = sender.concat(": ").concat(message);
        Player receiver_player = Players.playerByUsername(receiver);
        if(receiver_player != null && !receiver_player.getBlock().contains(sender)){
            if(!messages.containsKey(receiver)){
                messages.put(receiver, new ArrayList<>());
            }
            messages.get(receiver).add(message);
        }                  
    }       
    
    public static String pull(String receiver){
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> messages_to_receiver = messages.get(receiver);        
        while(messages_to_receiver != null && !messages_to_receiver.isEmpty()){ 
            stringBuilder.append("\n").append(messages_to_receiver.remove(0));
        } 
        return stringBuilder.toString();
    }
    
    public static void remove(String receiver){
        messages.remove(receiver);
    }

    public String process(String secret_identifier, String receiver, String message) {
        Player player = Players.getPlayer(secret_identifier);
        if(receiver.equalsIgnoreCase("Lobby")){
            Lobby.lobby_players().forEach(username -> {                   
                Messages.push(player.getUsername(), username, message);
            });
        } else if(receiver.equalsIgnoreCase("Game")){
            Games.game_players(Games.game_identifier(player)).forEach(game_player -> {
                Messages.push(player.getUsername(), game_player.getUsername(), message);
            }); 
        } else {
            Messages.push(player.getUsername(), receiver, message);  
            Messages.push(player.getUsername(), player.getUsername(), message);
        }
        return pull(player.getUsername());
    }
    
}