package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class Vencivel {
    
    private static final Vencivel instance = new Vencivel();
    
    private static final Map<String, Player> players = Collections.synchronizedMap(new HashMap<String, Player>());
    
    public static synchronized Vencivel instance() {
        return instance;
    }

    private Vencivel() {        
        vencible_actions();          
    }       
          
     private void vencible_actions(){       
        new Thread() {

            @Override
            public void run() {
                
                try {                                       
                    while(true){                        
                        Thread.sleep(3141);
                        if(vencivel_indisponivel()){
                            Player player = novo_vencivel();
                            String secret_identifier = Players.add(player);
                            Lobby.add(player.getUsername());
                            players.put(secret_identifier, player);
                        }                        
                        venciveis_playing();
                        challenges();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Vencivel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }.start();
        
    }
     
    private boolean vencivel_indisponivel(){
        List<String> playing = Games.playing();
        List<String> players = Players.player_names();
        for(String player: players){
            if(player.contains("Vencível") && !playing.contains(player)){
                return false;
            }
        }
        return true;
    }
    
    private Player novo_vencivel(){
        List<String> playing = Games.playing();
        String vencivel = "Vencível";
        int contador = 1;
        while(playing.contains(vencivel)){            
            contador = contador + 1;
            vencivel = "Vencível " + StringUtils.romano(contador);           
        }
        Player player = new Player();
        player.setId(vencivel.toLowerCase());
        player.setUsername(vencivel);
        return player;                
    }       
    
    private void challenges(){
        for (Map.Entry<String, Player> player : players.entrySet()) { 
            String processReturn = Lobby.instance().process(player.getKey());                        
            String challenged_start_index = "challenged=[";
            String challenged = processReturn.substring(processReturn.indexOf(challenged_start_index) + challenged_start_index.length());
            challenged = challenged.substring(0, challenged.indexOf("]"));
            if(challenged.length() == 0){ 
                String challenger_start_index = "challenger=[";                
                String challenger = processReturn.substring(processReturn.indexOf(challenger_start_index) + challenger_start_index.length());
                challenger = challenger.substring(0, challenger.indexOf("]"));
                String[] challengers = challenger.split(", ");
                if(challengers[0].length() != 0){ 
                    Lobby.instance().process(player.getKey(), "Aceitar", challengers[0]);                    
                }          
            }
        }         
    }
    
    private void venciveis_playing(){
        Map<String, Player> temp_players = new HashMap<>();
        temp_players.putAll(players);
        for (Map.Entry<String, Player> player : temp_players.entrySet()) {             
            String game_identifier = Games.game_identifier(player.getValue());
            if(game_identifier != null){
                String processReturn = Games.instance().process(player.getKey(), null, null, null); 
                                
                String me_start_index = "me={";
                String me = processReturn.substring(processReturn.indexOf(me_start_index) + me_start_index.length());
                me = me.substring(0, me.indexOf("}"));
                
                String player1_start_index = "player1={";
                String player1 = processReturn.substring(processReturn.indexOf(player1_start_index) + player1_start_index.length());
                player1 = player1.substring(0, player1.indexOf("}"));
                
                String player2_start_index = "player2={";
                String player2 = processReturn.substring(processReturn.indexOf(player2_start_index) + player2_start_index.length());
                player2 = player2.substring(0, player2.indexOf("}"));
                
                String turn_start_index = "turn={";
                String turn = processReturn.substring(processReturn.indexOf(turn_start_index) + turn_start_index.length());
                turn = turn.substring(0, turn.indexOf("}"));
                
                String winner_start_index = "winner={";
                String winner = processReturn.substring(processReturn.indexOf(winner_start_index) + winner_start_index.length());
                winner = winner.substring(0, winner.indexOf("}"));
                
                HashSet<Player> game_players = Games.game_players(game_identifier);
                boolean player1_playing = false;
                boolean player2_playing = false;
                for(Player game_player : game_players){
                    if(game_player.getUsername().equalsIgnoreCase(player1)){
                        player1_playing = true;
                    }
                    if(game_player.getUsername().equalsIgnoreCase(player2)){
                        player2_playing = true;
                    }
                }
                boolean other_player_exited = !(player1_playing && player2_playing);                                                                                      
                
                if(winner.equalsIgnoreCase("player1") || winner.equalsIgnoreCase("player2") || other_player_exited){ 
                    players.remove(player.getKey());
                    Players.logout(player.getKey());
                } else if((me.equalsIgnoreCase(player1) && turn.equalsIgnoreCase("player1")) || (me.equalsIgnoreCase(player2) && turn.equalsIgnoreCase("player2"))){                         
                    String moves = processReturn.substring(processReturn.indexOf("moves={"), processReturn.indexOf("contact=["));
                    String moves_p = moves.substring("moves={".length(), moves.indexOf("}"));
                    String[] moves_p_arr = moves_p.split(",");
                    moves = moves.substring(moves.indexOf("}") + 1);
                    if(!moves.isEmpty()){                            
                        List<List<String>> all_moves = new ArrayList<>();
                        for(String m_p : moves_p_arr){ 
                            String str_pos = moves.substring(m_p.concat("={").length(), moves.indexOf("}"));
                            String[] str_p_arr = str_pos.split(",");
                            for(String p : str_p_arr){ 
                                List<String> a_move = new ArrayList<>();
                                a_move.add(m_p);
                                a_move.add(p);
                                all_moves.add(a_move);
                            }
                            moves = moves.substring(moves.indexOf("}") + 1);
                        }
                        Random r = new Random();
                        List<String> a_move = all_moves.get(r.nextInt(all_moves.size()));
                        Games.instance().process(player.getKey(), a_move.get(0), a_move.get(1), null);                              
                    }
                }
            } 
        }
    }        

}
