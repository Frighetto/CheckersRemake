package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lucas Fernando Frighetto
 * 
 */
public class Games {
    
    private static final Games instance = new Games();

    public static synchronized Games instance() {
        return instance;
    }

    private Games() {
        
    }
    
    private static final Map<String, Game> boardgames = Collections.synchronizedMap(new HashMap<String, Game>());    
    private static final Map<String, HashSet<Player>> games = Collections.synchronizedMap(new HashMap<String, HashSet<Player>>());       
    
    public static String new_game(Player player1, Player player2) {          
        Game boardgame = new Game(player1, player2);        
        boardgames.put(boardgame.getGame_identifier(), boardgame);
        games.put(boardgame.getGame_identifier(), new HashSet<>());
        games.get(boardgame.getGame_identifier()).add(player1);
        games.get(boardgame.getGame_identifier()).add(player2);        
        return boardgame.getGame_identifier();
    }
    
    public static String game_identifier(Player player){
        for (Map.Entry<String, HashSet<Player>> game : games.entrySet()) { 
            for (Player game_player : game.getValue()) {
                if(game_player.getId().equalsIgnoreCase(player.getId())){
                    return game.getKey();
                }
            }
        }
        return null;
    }
    
    public static HashSet<Player> game_players(String game_identifier){
        return games.get(game_identifier);
    }
    
    public static List<String> playing(){
        List<String> playing = new ArrayList<>();
        for (Map.Entry<String, Game> boardgame : boardgames.entrySet()) { 
            if(games.get(boardgame.getKey()).contains(boardgame.getValue().getPlayer1()) && games.get(boardgame.getKey()).contains(boardgame.getValue().getPlayer2())){
                playing.add(boardgame.getValue().getPlayer1().getUsername());
                playing.add(boardgame.getValue().getPlayer2().getUsername());
            }
        }
        return playing;
    }
    
    public static void view(String game_identifier, Player player){
        games.get(game_identifier).add(player);
    }
    
    public static void remove(String player_name){
        Player player = Players.playerByUsername(player_name);
        String game_identifier = game_identifier(player);
        if(game_identifier != null){
            games.get(game_identifier).remove(player);
            if(games.get(game_identifier).isEmpty()){
                games.remove(game_identifier);
                boardgames.remove(game_identifier);
            }
        }
    }
    
    private Boolean not_playing(Player game_player, Game game){
        return !game_player.getId().equalsIgnoreCase(game.getPlayer1().getId()) && !game_player.getId().equalsIgnoreCase(game.getPlayer2().getId());
    }

    public String process(String secret_identifier, String position, String move, Integer index_moves) {
        Player player = Players.getPlayer(secret_identifier);
        String game_identifier = game_identifier(player);
        Game game = boardgames.get(game_identifier);
        if(move != null){
            move(player, game, position, move);
        }
        return attributesValues(player, game, index_moves);
    }        
    
    private void move(Player player, Game game, String position, String move){
        if(game.getCheckers().toString().startsWith("turn={player1}")){
            if(player.getId().equalsIgnoreCase(game.getPlayer1().getId())){
                game.getCheckers().move(position, move);
            }
        } else if(game.getCheckers().toString().startsWith("turn={player2}")){
            if(player.getId().equalsIgnoreCase(game.getPlayer2().getId())){
                game.getCheckers().move(position, move);
            }
        } 
    }
    
    private String moves_options(Player player, Game game) {   
        if(game.getCheckers().toString().startsWith("turn={player1}")){
            if(player.getId().equalsIgnoreCase(game.getPlayer1().getId())){
                return game.getCheckers().moves_options();
            }
        } else if(game.getCheckers().toString().startsWith("turn={player2}")){
            if(player.getId().equalsIgnoreCase(game.getPlayer2().getId())){
                return game.getCheckers().moves_options();
            }
        }        
        return new String();
    }    
    
    private String attributesValues(Player player, Game game, Integer index_moves){                                            
        List<String> contact = new ArrayList<>(player.getContact());
        List<String> block = new ArrayList<>(player.getBlock());       
        List<String> online = new ArrayList<>(Players.player_names());
        List<String> viewers = new ArrayList<>();
       
        games.get(game.getGame_identifier()).stream().filter(game_player -> (not_playing(game_player, game))).forEachOrdered(game_player -> {
            viewers.add(game_player.getId());
        });                
                        
        StringBuilder attributeValues = new StringBuilder();
        attributeValues.append("me").append("={").append(player.getUsername()).append("}");
        attributeValues.append("player1").append("={").append(game.getPlayer1().getUsername()).append("}");
        attributeValues.append("player2").append("={").append(game.getPlayer2().getUsername()).append("}");
        attributeValues.append(game.getCheckers().toString());               
        attributeValues.append(moves_options(player, game));                          
        attributeValues.append(game.getCheckers().moves(index_moves));
        attributeValues.append("contact").append("=").append(contact.toString());
        attributeValues.append("block").append("=").append(block.toString());
        attributeValues.append("online").append("=").append(online.toString());
        attributeValues.append("viewers").append("=").append(viewers.toString());               
        attributeValues.append(Messages.pull(player.getUsername()));
        
        return attributeValues.toString();
    }
        
}