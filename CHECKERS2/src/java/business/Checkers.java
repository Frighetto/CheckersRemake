package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class Checkers {   
    
    private enum CHECKER {
        STARTER_PIECE, STARTER_KING,
        SECONDARY_PIECE, SECONDARY_KING
    }
    
    private enum SQUARE { 
        A8, B8, C8, D8, E8, F8, G8, H8,
        A7, B7, C7, D7, E7, F7, G7, H7,
        A6, B6, C6, D6, E6, F6, G6, H6,
        A5, B5, C5, D5, E5, F5, G5, H5,
        A4, B4, C4, D4, E4, F4, G4, H4,
        A3, B3, C3, D3, E3, F3, G3, H3,
        A2, B2, C2, D2, E2, F2, G2, H2,
        A1, B1, C1, D1, E1, F1, G1, H1                                                        
    }          
    
    private CHECKER turn;
    private final HashMap<SQUARE, CHECKER> positions;
    private final HashMap<SQUARE, HashSet<SQUARE>> moves;   
    private final List<SQUARE> origin_moves = new ArrayList<>();
    private final List<SQUARE> destiny_moves = new ArrayList<>();
    private Boolean moves_is_capture = false;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("turn={").append(turn.equals(CHECKER.STARTER_KING) ? "player1" : "player2").append("}");
        sb.append("positions={");
        Boolean first_position = true;
        for (Map.Entry<SQUARE, CHECKER> position : positions.entrySet()) { 
            if(!first_position){
                sb.append(",");                
            }
            first_position = false;
            sb.append(position.getKey());            
        }
        sb.append("}");
        positions.entrySet().forEach(position -> {
            sb.append(position.getKey()).append("={").append(position.getValue()).append("}");
        });
        sb.append("winner={").append(moves.isEmpty() ? (turn.equals(CHECKER.STARTER_KING) ? "player2" : "player1") : "").append("}");
        
        return sb.toString();
    }       
    
    public String moves_options(){
        StringBuilder sb = new StringBuilder();
        Boolean first_position = true;
        sb.append("moves={");        
        for (Map.Entry<SQUARE, HashSet<SQUARE>> move : moves.entrySet()) { 
            if(!first_position){
                sb.append(",");                
            }
            first_position = false;
            sb.append(move.getKey());            
        }
        sb.append("}");
        for (Map.Entry<SQUARE, HashSet<SQUARE>> position : moves.entrySet()) { 
            sb.append(position.getKey()).append("={");
            first_position = true;
            for (SQUARE move : position.getValue()) { 
                if(!first_position){
                    sb.append(",");                    
                }
                first_position = false;
                sb.append(move);
            }            
            sb.append("}");                  
        }        
                
        return sb.toString();
    }
    
    public String moves(Integer index_moves){
        StringBuilder sb = new StringBuilder();       
        if(index_moves != null){
            int last_index_moves = origin_moves.size() - 1;
            if(last_index_moves > index_moves){   
                sb.append("origin_moves=").append(origin_moves.subList(index_moves + 1, last_index_moves + 1));
                sb.append("destiny_moves=").append(destiny_moves.subList(index_moves + 1, last_index_moves + 1));
            }
        }
        if(index_moves == null && !origin_moves.isEmpty()){
            sb.append("origin_moves=").append(origin_moves);
            sb.append("destiny_moves=").append(destiny_moves);
        }
        return sb.toString();
    }
    
    private HashMap<SQUARE, CHECKER> initialize(){        
        HashMap<SQUARE, CHECKER> checkers_positions = new HashMap<>();
        checkers_positions.put(SQUARE.A1, CHECKER.STARTER_PIECE);
        checkers_positions.put(SQUARE.C1, CHECKER.STARTER_PIECE);
        checkers_positions.put(SQUARE.E1, CHECKER.STARTER_PIECE);
        checkers_positions.put(SQUARE.G1, CHECKER.STARTER_PIECE);
        checkers_positions.put(SQUARE.H2, CHECKER.STARTER_PIECE);
        checkers_positions.put(SQUARE.F2, CHECKER.STARTER_PIECE);
        checkers_positions.put(SQUARE.D2, CHECKER.STARTER_PIECE);
        checkers_positions.put(SQUARE.B2, CHECKER.STARTER_PIECE);
        checkers_positions.put(SQUARE.A3, CHECKER.STARTER_PIECE);
        checkers_positions.put(SQUARE.C3, CHECKER.STARTER_PIECE);
        checkers_positions.put(SQUARE.E3, CHECKER.STARTER_PIECE);
        checkers_positions.put(SQUARE.G3, CHECKER.STARTER_PIECE);
        
        checkers_positions.put(SQUARE.H6, CHECKER.SECONDARY_PIECE);
        checkers_positions.put(SQUARE.F6, CHECKER.SECONDARY_PIECE);
        checkers_positions.put(SQUARE.D6, CHECKER.SECONDARY_PIECE);
        checkers_positions.put(SQUARE.B6, CHECKER.SECONDARY_PIECE);
        checkers_positions.put(SQUARE.A7, CHECKER.SECONDARY_PIECE);
        checkers_positions.put(SQUARE.C7, CHECKER.SECONDARY_PIECE);
        checkers_positions.put(SQUARE.E7, CHECKER.SECONDARY_PIECE);
        checkers_positions.put(SQUARE.G7, CHECKER.SECONDARY_PIECE);
        checkers_positions.put(SQUARE.H8, CHECKER.SECONDARY_PIECE);
        checkers_positions.put(SQUARE.F8, CHECKER.SECONDARY_PIECE);
        checkers_positions.put(SQUARE.D8, CHECKER.SECONDARY_PIECE);
        checkers_positions.put(SQUARE.B8, CHECKER.SECONDARY_PIECE);
        return checkers_positions;
    }    
    
    private void next_moves(){        
        if(this.turn.equals(CHECKER.STARTER_KING)){            
            starters_next_moves();
        } else {
            secondarys_next_moves();
        }                
    }                
    
    public void move(String position, String move){
        move(SQUARE.valueOf(position), SQUARE.valueOf(move));
    }
    
    private void move(SQUARE position, SQUARE move){        
        if(position != null && move != null){
            if(this.moves.containsKey(position) && this.moves.get(position).contains(move)){
                this.origin_moves.add(position);
                this.destiny_moves.add(move);
                capture(position, move);
                move_checker(position, move);
                if(this.moves_is_capture){
                    this.next_moves();
                    if(!this.moves_is_capture){
                        this.turn = this.turn.equals(CHECKER.STARTER_KING) ? CHECKER.SECONDARY_KING : CHECKER.STARTER_KING; 
                        this.next_moves();
                    }
                } else {
                    this.turn = this.turn.equals(CHECKER.STARTER_KING) ? CHECKER.SECONDARY_KING : CHECKER.STARTER_KING; 
                    this.next_moves();
                }                                                                                            
            }
        }        
    }
    
    private void move_checker(SQUARE position, SQUARE move){
        CHECKER checker = this.positions.remove(position);
        if(checker.equals(CHECKER.STARTER_PIECE)){
            if(move.equals(SQUARE.B8) || move.equals(SQUARE.D8) || move.equals(SQUARE.F8) || move.equals(SQUARE.H8)){
                checker = CHECKER.STARTER_KING;
            }
        }
        if(checker.equals(CHECKER.SECONDARY_PIECE)){
            if(move.equals(SQUARE.A1) || move.equals(SQUARE.C1) || move.equals(SQUARE.E1) || move.equals(SQUARE.G1)){
                checker = CHECKER.SECONDARY_KING;
            }
        }
        this.positions.put(move, checker);
    }
    
    private void capture(SQUARE position, SQUARE move){        
        
        capture(position, move, SQUARE.A1, SQUARE.B2, SQUARE.C3);
        capture(position, move, SQUARE.C1, SQUARE.B2, SQUARE.A3);
        capture(position, move, SQUARE.C1, SQUARE.D2, SQUARE.E3);
        
        capture(position, move, SQUARE.E1, SQUARE.D2, SQUARE.C3);
        capture(position, move, SQUARE.E1, SQUARE.F2, SQUARE.G3);
        
        capture(position, move, SQUARE.G1, SQUARE.F2, SQUARE.E3);
        
        capture(position, move, SQUARE.B2, SQUARE.C3, SQUARE.D4);
        
        capture(position, move, SQUARE.D2, SQUARE.C3, SQUARE.B4);
        capture(position, move, SQUARE.D2, SQUARE.E3, SQUARE.F4);    
        
        capture(position, move, SQUARE.F2, SQUARE.E3, SQUARE.D4);
        capture(position, move, SQUARE.F2, SQUARE.G3, SQUARE.H4); 
        
        capture(position, move, SQUARE.H2, SQUARE.G3, SQUARE.F4);
        
        capture(position, move, SQUARE.A3, SQUARE.B2, SQUARE.C1);
        capture(position, move, SQUARE.A3, SQUARE.B4, SQUARE.C5); 
        
        capture(position, move, SQUARE.C3, SQUARE.B2, SQUARE.A1);
        capture(position, move, SQUARE.C3, SQUARE.D2, SQUARE.E1);
        capture(position, move, SQUARE.C3, SQUARE.B4, SQUARE.A5);
        capture(position, move, SQUARE.C3, SQUARE.D4, SQUARE.E5);   
        
        capture(position, move, SQUARE.E3, SQUARE.D2, SQUARE.C1);
        capture(position, move, SQUARE.E3, SQUARE.F2, SQUARE.G1);
        capture(position, move, SQUARE.E3, SQUARE.D4, SQUARE.C5);
        capture(position, move, SQUARE.E3, SQUARE.F4, SQUARE.G5); 
        
        capture(position, move, SQUARE.G3, SQUARE.F2, SQUARE.E1);        
        capture(position, move, SQUARE.G3, SQUARE.F4, SQUARE.E5); 
        
        capture(position, move, SQUARE.B4, SQUARE.C3, SQUARE.D2);
        capture(position, move, SQUARE.B4, SQUARE.C5, SQUARE.D6);   
        
        capture(position, move, SQUARE.D4, SQUARE.C3, SQUARE.B2);
        capture(position, move, SQUARE.D4, SQUARE.E3, SQUARE.F2);
        capture(position, move, SQUARE.D4, SQUARE.C5, SQUARE.B6);
        capture(position, move, SQUARE.D4, SQUARE.E5, SQUARE.F6); 
        
        capture(position, move, SQUARE.F4, SQUARE.E3, SQUARE.D2);
        capture(position, move, SQUARE.F4, SQUARE.G3, SQUARE.H2);
        capture(position, move, SQUARE.F4, SQUARE.E5, SQUARE.D6);
        capture(position, move, SQUARE.F4, SQUARE.G5, SQUARE.H6);  
        
        capture(position, move, SQUARE.H4, SQUARE.G3, SQUARE.F2);
        capture(position, move, SQUARE.H4, SQUARE.G5, SQUARE.F6);
        
        capture(position, move, SQUARE.A5, SQUARE.B4, SQUARE.C3);
        capture(position, move, SQUARE.A5, SQUARE.B6, SQUARE.C7);  
        
        capture(position, move, SQUARE.C5, SQUARE.B4, SQUARE.A3);
        capture(position, move, SQUARE.C5, SQUARE.D4, SQUARE.E3);
        capture(position, move, SQUARE.C5, SQUARE.B6, SQUARE.A7);
        capture(position, move, SQUARE.C5, SQUARE.D6, SQUARE.E7);   
        
        capture(position, move, SQUARE.E5, SQUARE.D4, SQUARE.C3);
        capture(position, move, SQUARE.E5, SQUARE.F4, SQUARE.G3);
        capture(position, move, SQUARE.E5, SQUARE.D6, SQUARE.C7);
        capture(position, move, SQUARE.E5, SQUARE.F6, SQUARE.G7); 
        
        capture(position, move, SQUARE.G5, SQUARE.F4, SQUARE.E3);
        capture(position, move, SQUARE.G5, SQUARE.F6, SQUARE.E7);
        
        capture(position, move, SQUARE.B6, SQUARE.C5, SQUARE.D4);
        capture(position, move, SQUARE.B6, SQUARE.C7, SQUARE.D8);
        
        capture(position, move, SQUARE.D6, SQUARE.C5, SQUARE.B4);
        capture(position, move, SQUARE.D6, SQUARE.E5, SQUARE.F4);
        capture(position, move, SQUARE.D6, SQUARE.C7, SQUARE.B8);
        capture(position, move, SQUARE.D6, SQUARE.E7, SQUARE.F8);
        
        capture(position, move, SQUARE.F6, SQUARE.E5, SQUARE.D4);
        capture(position, move, SQUARE.F6, SQUARE.G5, SQUARE.H4);
        capture(position, move, SQUARE.F6, SQUARE.E7, SQUARE.D8);
        capture(position, move, SQUARE.F6, SQUARE.G7, SQUARE.H8);
        
        capture(position, move, SQUARE.H6, SQUARE.G5, SQUARE.F4);
        capture(position, move, SQUARE.H6, SQUARE.G7, SQUARE.F8);
        
        capture(position, move, SQUARE.A7, SQUARE.B6, SQUARE.C5);
        
        capture(position, move, SQUARE.C7, SQUARE.B6, SQUARE.A5);
        capture(position, move, SQUARE.C7, SQUARE.D6, SQUARE.E5); 
        
        capture(position, move, SQUARE.E7, SQUARE.D6, SQUARE.C5);
        capture(position, move, SQUARE.E7, SQUARE.F6, SQUARE.G5);
        
        capture(position, move, SQUARE.G7, SQUARE.F6, SQUARE.E5);
        
        capture(position, move, SQUARE.B8, SQUARE.C7, SQUARE.D6);
        
        capture(position, move, SQUARE.D8, SQUARE.C7, SQUARE.B6);
        capture(position, move, SQUARE.D8, SQUARE.E7, SQUARE.F6);
        
        capture(position, move, SQUARE.F8, SQUARE.E7, SQUARE.D6);
        capture(position, move, SQUARE.F8, SQUARE.G7, SQUARE.H6);
        
        capture(position, move, SQUARE.H8, SQUARE.G7, SQUARE.F6);
        
    }
    
    private void capture(SQUARE capture_position, SQUARE capture_move, SQUARE position, SQUARE captureble,SQUARE move){
        if(position.equals(capture_position) && move.equals(capture_move)){
            this.positions.remove(captureble);
        }
    }

    public Checkers() {     
        this.turn = CHECKER.STARTER_KING;
        this.positions = initialize();
        this.moves = new HashMap<>();       
        this.next_moves();
    }
    
    private void add(HashMap<SQUARE, HashSet<SQUARE>> map, SQUARE key, SQUARE value){
        if(!map.containsKey(key)){
            map.put(key, new HashSet<>());
        }
        map.get(key).add(value);
    }
    
    private void try_add_move_capture(CHECKER required_checker, CHECKER opposite_piece, CHECKER opposite_king, SQUARE square, SQUARE capture, SQUARE move){
        if(try_add_move(required_checker, square, move)){
            if(capture != null){                
                CHECKER capture_checker = this.positions.get(capture);
                if(capture_checker != null){
                    if(capture_checker.equals(opposite_piece) || capture_checker.equals(opposite_king)){                        
                        add(this.moves, square, move);
                    }
                }
            } else {                                       
                add(this.moves, square, move); 
            }
            
        }        
    }        
    
    private Boolean try_add_move(CHECKER required_checker, SQUARE square, SQUARE move){        
        CHECKER move_checker = this.positions.get(move);
        if(move_checker == null){
            CHECKER square_checker = this.positions.get(square);        
            if(square_checker != null){                   
                if(square_checker.equals(required_checker)){   
                    return Boolean.TRUE;
                }
                if(required_checker.equals(CHECKER.STARTER_PIECE) && square_checker.equals(CHECKER.STARTER_KING)){   
                    return Boolean.TRUE;
                }
                if(required_checker.equals(CHECKER.SECONDARY_PIECE) && square_checker.equals(CHECKER.SECONDARY_KING)){   
                    return Boolean.TRUE;
                }                                
            }
        }
        return Boolean.FALSE;
    }
    
    private void starters_next_moves(){
        this.moves.clear();           
        
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A1, SQUARE.B2, SQUARE.C3);
        
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C1, SQUARE.B2, SQUARE.A3);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C1, SQUARE.D2, SQUARE.E3);
        
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E1, SQUARE.D2, SQUARE.C3);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E1, SQUARE.F2, SQUARE.G3);
        
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G1, SQUARE.F2, SQUARE.E3);
        
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B2, SQUARE.C3, SQUARE.D4);
        
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D2, SQUARE.C3, SQUARE.B4);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D2, SQUARE.E3, SQUARE.F4);    
        
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F2, SQUARE.E3, SQUARE.D4);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F2, SQUARE.G3, SQUARE.H4); 
        
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H2, SQUARE.G3, SQUARE.F4);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A3, SQUARE.B2, SQUARE.C1);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A3, SQUARE.B4, SQUARE.C5); 
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C3, SQUARE.B2, SQUARE.A1);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C3, SQUARE.D2, SQUARE.E1);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C3, SQUARE.B4, SQUARE.A5);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C3, SQUARE.D4, SQUARE.E5);   
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E3, SQUARE.D2, SQUARE.C1);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E3, SQUARE.F2, SQUARE.G1);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E3, SQUARE.D4, SQUARE.C5);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E3, SQUARE.F4, SQUARE.G5); 
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G3, SQUARE.F2, SQUARE.E1);        
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G3, SQUARE.F4, SQUARE.E5); 
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B4, SQUARE.C3, SQUARE.D2);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B4, SQUARE.C5, SQUARE.D6);   
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D4, SQUARE.C3, SQUARE.B2);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D4, SQUARE.E3, SQUARE.F2);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D4, SQUARE.C5, SQUARE.B6);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D4, SQUARE.E5, SQUARE.F6); 
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F4, SQUARE.E3, SQUARE.D2);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F4, SQUARE.G3, SQUARE.H2);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F4, SQUARE.E5, SQUARE.D6);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F4, SQUARE.G5, SQUARE.H6);  
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H4, SQUARE.G3, SQUARE.F2);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H4, SQUARE.G5, SQUARE.F6);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A5, SQUARE.B4, SQUARE.C3);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A5, SQUARE.B6, SQUARE.C7);  
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C5, SQUARE.B4, SQUARE.A3);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C5, SQUARE.D4, SQUARE.E3);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C5, SQUARE.B6, SQUARE.A7);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C5, SQUARE.D6, SQUARE.E7);   
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E5, SQUARE.D4, SQUARE.C3);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E5, SQUARE.F4, SQUARE.G3);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E5, SQUARE.D6, SQUARE.C7);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E5, SQUARE.F6, SQUARE.G7); 
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G5, SQUARE.F4, SQUARE.E3);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G5, SQUARE.F6, SQUARE.E7);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B6, SQUARE.C5, SQUARE.D4);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B6, SQUARE.C7, SQUARE.D8);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D6, SQUARE.C5, SQUARE.B4);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D6, SQUARE.E5, SQUARE.F4);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D6, SQUARE.C7, SQUARE.B8);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D6, SQUARE.E7, SQUARE.F8);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F6, SQUARE.E5, SQUARE.D4);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F6, SQUARE.G5, SQUARE.H4);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F6, SQUARE.E7, SQUARE.D8);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F6, SQUARE.G7, SQUARE.H8);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H6, SQUARE.G5, SQUARE.F4);
        try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H6, SQUARE.G7, SQUARE.F8);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A7, SQUARE.B6, SQUARE.C5);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C7, SQUARE.B6, SQUARE.A5);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C7, SQUARE.D6, SQUARE.E5); 
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E7, SQUARE.D6, SQUARE.C5);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E7, SQUARE.F6, SQUARE.G5);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G7, SQUARE.F6, SQUARE.E5);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B8, SQUARE.C7, SQUARE.D6);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D8, SQUARE.C7, SQUARE.B6);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D8, SQUARE.E7, SQUARE.F6);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F8, SQUARE.E7, SQUARE.D6);
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F8, SQUARE.G7, SQUARE.H6);
        
        try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H8, SQUARE.G7, SQUARE.F6);
        
        this.moves_is_capture = !this.moves.isEmpty();
        
        if(!this.moves_is_capture){            
            
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A1, null, SQUARE.B2);

            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C1, null, SQUARE.B2);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C1, null, SQUARE.D2);

            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E1, null, SQUARE.D2);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E1, null, SQUARE.F2);        

            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G1, null, SQUARE.F2);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G1, null, SQUARE.H2);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B2, null, SQUARE.A1);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B2, null, SQUARE.C1);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B2, null, SQUARE.A3);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B2, null, SQUARE.C3);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D2, null, SQUARE.C1);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D2, null, SQUARE.E1);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D2, null, SQUARE.C3);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D2, null, SQUARE.E3);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F2, null, SQUARE.E1);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F2, null, SQUARE.G1);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F2, null, SQUARE.E3);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F2, null, SQUARE.G3);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H2, null, SQUARE.G1);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H2, null, SQUARE.G3);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A3, null, SQUARE.B2);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A3, null, SQUARE.B4);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C3, null, SQUARE.B2);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C3, null, SQUARE.D2);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C3, null, SQUARE.B4);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C3, null, SQUARE.D4);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E3, null, SQUARE.D2);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E3, null, SQUARE.F2);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E3, null, SQUARE.D4);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E3, null, SQUARE.F4);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G3, null, SQUARE.F2);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G3, null, SQUARE.H2);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G3, null, SQUARE.F4);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G3, null, SQUARE.H4);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B4, null, SQUARE.A3);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B4, null, SQUARE.C3);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B4, null, SQUARE.A5);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B4, null, SQUARE.C5);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D4, null, SQUARE.C3);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D4, null, SQUARE.E3);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D4, null, SQUARE.C5);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D4, null, SQUARE.E5);                       

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F4, null, SQUARE.E3);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F4, null, SQUARE.G3);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F4, null, SQUARE.E5);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F4, null, SQUARE.G5);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H4, null, SQUARE.G3);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H4, null, SQUARE.G5);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A5, null, SQUARE.B4);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A5, null, SQUARE.B6);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C5, null, SQUARE.B4);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C5, null, SQUARE.D4);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C5, null, SQUARE.B6);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C5, null, SQUARE.D6);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E5, null, SQUARE.D4);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E5, null, SQUARE.F4);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E5, null, SQUARE.D6);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E5, null, SQUARE.F6);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G5, null, SQUARE.F4);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G5, null, SQUARE.H4);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G5, null, SQUARE.F6);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G5, null, SQUARE.H6);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B6, null, SQUARE.A5);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B6, null, SQUARE.C5);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B6, null, SQUARE.A7);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B6, null, SQUARE.C7);        

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D6, null, SQUARE.C5);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D6, null, SQUARE.E5);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D6, null, SQUARE.C7);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D6, null, SQUARE.E7);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F6, null, SQUARE.E5);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F6, null, SQUARE.G5);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F6, null, SQUARE.E7);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F6, null, SQUARE.G7);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H6, null, SQUARE.G5);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H6, null, SQUARE.G7);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A7, null, SQUARE.B6);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.A7, null, SQUARE.B8);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C7, null, SQUARE.B6);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C7, null, SQUARE.D6);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C7, null, SQUARE.B8);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.C7, null, SQUARE.D8);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E7, null, SQUARE.D6);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E7, null, SQUARE.F6);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E7, null, SQUARE.D8);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.E7, null, SQUARE.F8);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G7, null, SQUARE.F6);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G7, null, SQUARE.H6);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G7, null, SQUARE.F8);
            try_add_move_capture(CHECKER.STARTER_PIECE, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.G7, null, SQUARE.H8);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B8, null, SQUARE.A7);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.B8, null, SQUARE.C7);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D8, null, SQUARE.C7);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.D8, null, SQUARE.E7);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F8, null, SQUARE.E7);
            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.F8, null, SQUARE.G7);

            try_add_move_capture(CHECKER.STARTER_KING, CHECKER.SECONDARY_PIECE, CHECKER.SECONDARY_KING, SQUARE.H8, null, SQUARE.G7);
        }
        
    }
    
    private void secondarys_next_moves(){                
        this.moves.clear();
        
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A1, SQUARE.B2, SQUARE.C3);
        
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C1, SQUARE.B2, SQUARE.A3);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C1, SQUARE.D2, SQUARE.E3);
        
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E1, SQUARE.D2, SQUARE.C3);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E1, SQUARE.F2, SQUARE.G3);
        
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G1, SQUARE.F2, SQUARE.E3); 
        
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B2, SQUARE.C3, SQUARE.D4); 
        
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D2, SQUARE.C3, SQUARE.B4);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D2, SQUARE.E3, SQUARE.F4); 
        
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F2, SQUARE.E3, SQUARE.D4);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F2, SQUARE.G3, SQUARE.H4);
        
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H2, SQUARE.G3, SQUARE.F4);  
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A3, SQUARE.B2, SQUARE.C1);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A3, SQUARE.B4, SQUARE.C5); 
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C3, SQUARE.B2, SQUARE.A1);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C3, SQUARE.D2, SQUARE.E1);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C3, SQUARE.B4, SQUARE.A5);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C3, SQUARE.D4, SQUARE.E5); 
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E3, SQUARE.D2, SQUARE.C1);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E3, SQUARE.F2, SQUARE.G1);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E3, SQUARE.D4, SQUARE.C5);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E3, SQUARE.F4, SQUARE.G5);  
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G3, SQUARE.F2, SQUARE.E1);        
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G3, SQUARE.F4, SQUARE.E5); 
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B4, SQUARE.C3, SQUARE.D2);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B4, SQUARE.C5, SQUARE.D6);       
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D4, SQUARE.C3, SQUARE.B2);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D4, SQUARE.E3, SQUARE.F2);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D4, SQUARE.C5, SQUARE.B6);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D4, SQUARE.E5, SQUARE.F6); 
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F4, SQUARE.E3, SQUARE.D2);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F4, SQUARE.G3, SQUARE.H2);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F4, SQUARE.E5, SQUARE.D6);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F4, SQUARE.G5, SQUARE.H6); 
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H4, SQUARE.G3, SQUARE.F2);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H4, SQUARE.G5, SQUARE.F6); 
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A5, SQUARE.B4, SQUARE.C3);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A5, SQUARE.B6, SQUARE.C7); 
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C5, SQUARE.B4, SQUARE.A3);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C5, SQUARE.D4, SQUARE.E3);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C5, SQUARE.B6, SQUARE.A7);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C5, SQUARE.D6, SQUARE.E7);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E5, SQUARE.D4, SQUARE.C3);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E5, SQUARE.F4, SQUARE.G3);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E5, SQUARE.D6, SQUARE.C7);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E5, SQUARE.F6, SQUARE.G7);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G5, SQUARE.F4, SQUARE.E3);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G5, SQUARE.F6, SQUARE.E7);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B6, SQUARE.C5, SQUARE.D4);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B6, SQUARE.C7, SQUARE.D8);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D6, SQUARE.C5, SQUARE.B4);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D6, SQUARE.E5, SQUARE.F4);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D6, SQUARE.C7, SQUARE.B8);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D6, SQUARE.E7, SQUARE.F8);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F6, SQUARE.E5, SQUARE.D4);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F6, SQUARE.G5, SQUARE.H4);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F6, SQUARE.E7, SQUARE.D8);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F6, SQUARE.G7, SQUARE.H8);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H6, SQUARE.G5, SQUARE.F4);
        try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H6, SQUARE.G7, SQUARE.F8);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A7, SQUARE.B6, SQUARE.C5);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C7, SQUARE.B6, SQUARE.A5);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C7, SQUARE.D6, SQUARE.E5);  
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E7, SQUARE.D6, SQUARE.C5);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E7, SQUARE.F6, SQUARE.G5);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G7, SQUARE.F6, SQUARE.E5);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B8, SQUARE.C7, SQUARE.D6);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D8, SQUARE.C7, SQUARE.B6);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D8, SQUARE.E7, SQUARE.F6);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F8, SQUARE.E7, SQUARE.D6);
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F8, SQUARE.G7, SQUARE.H6);
        
        try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H8, SQUARE.G7, SQUARE.F6);
        
        this.moves_is_capture = !this.moves.isEmpty();
        
        if(!this.moves_is_capture){    
                
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A1, null, SQUARE.B2);

            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C1, null, SQUARE.B2);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C1, null, SQUARE.D2);

            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E1, null, SQUARE.D2);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E1, null, SQUARE.F2);

            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G1, null, SQUARE.F2);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G1, null, SQUARE.H2);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B2, null, SQUARE.A1);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B2, null, SQUARE.C1);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B2, null, SQUARE.A3);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B2, null, SQUARE.C3);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D2, null, SQUARE.C1);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D2, null, SQUARE.E1);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D2, null, SQUARE.C3);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D2, null, SQUARE.E3);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F2, null, SQUARE.E1);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F2, null, SQUARE.G1);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F2, null, SQUARE.E3);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F2, null, SQUARE.G3);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H2, null, SQUARE.G1);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H2, null, SQUARE.G3);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A3, null, SQUARE.B2);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A3, null, SQUARE.B4);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C3, null, SQUARE.B2);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C3, null, SQUARE.D2);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C3, null, SQUARE.B4);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C3, null, SQUARE.D4);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E3, null, SQUARE.D2);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E3, null, SQUARE.F2);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E3, null, SQUARE.D4);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E3, null, SQUARE.F4);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G3, null, SQUARE.F2);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G3, null, SQUARE.H2);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G3, null, SQUARE.F4);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G3, null, SQUARE.H4);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B4, null, SQUARE.A3);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B4, null, SQUARE.C3);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B4, null, SQUARE.A5);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B4, null, SQUARE.C5);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D4, null, SQUARE.C3);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D4, null, SQUARE.E3);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D4, null, SQUARE.C5);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D4, null, SQUARE.E5);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F4, null, SQUARE.E3);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F4, null, SQUARE.G3);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F4, null, SQUARE.E5);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F4, null, SQUARE.G5);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H4, null, SQUARE.G3);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H4, null, SQUARE.G5);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A5, null, SQUARE.B4);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A5, null, SQUARE.B6);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C5, null, SQUARE.B4);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C5, null, SQUARE.D4);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C5, null, SQUARE.B6);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C5, null, SQUARE.D6);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E5, null, SQUARE.D4);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E5, null, SQUARE.F4);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E5, null, SQUARE.D6);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E5, null, SQUARE.F6);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G5, null, SQUARE.F4);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G5, null, SQUARE.H4);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G5, null, SQUARE.F6);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G5, null, SQUARE.H6);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B6, null, SQUARE.A5);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B6, null, SQUARE.C5);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B6, null, SQUARE.A7);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B6, null, SQUARE.C7);        

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D6, null, SQUARE.C5);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D6, null, SQUARE.E5);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D6, null, SQUARE.C7);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D6, null, SQUARE.E7);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F6, null, SQUARE.E5);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F6, null, SQUARE.G5);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F6, null, SQUARE.E7);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F6, null, SQUARE.G7);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H6, null, SQUARE.G5);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H6, null, SQUARE.G7);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A7, null, SQUARE.B6);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.A7, null, SQUARE.B8);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C7, null, SQUARE.B6);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C7, null, SQUARE.D6);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C7, null, SQUARE.B8);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.C7, null, SQUARE.D8);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E7, null, SQUARE.D6);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E7, null, SQUARE.F6);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E7, null, SQUARE.D8);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.E7, null, SQUARE.F8);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G7, null, SQUARE.F6);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G7, null, SQUARE.H6);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G7, null, SQUARE.F8);
            try_add_move_capture(CHECKER.SECONDARY_KING, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.G7, null, SQUARE.H8);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B8, null, SQUARE.A7);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.B8, null, SQUARE.C7);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D8, null, SQUARE.C7);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.D8, null, SQUARE.E7);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F8, null, SQUARE.E7);
            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.F8, null, SQUARE.G7);

            try_add_move_capture(CHECKER.SECONDARY_PIECE, CHECKER.STARTER_PIECE, CHECKER.STARTER_KING, SQUARE.H8, null, SQUARE.G7);
        
        }    
    }
    
    /*
    XX B8 XX D8 XX F8 XX H8
    A7 XX C7 XX E7 XX G7 XX
    XX B6 XX D6 XX F6 XX H6
    A5 XX C5 XX E5 XX G5 XX
    XX B4 XX D4 XX F4 XX H4
    A3 XX C3 XX E3 XX G3 XX
    XX B2 XX D2 XX F2 XX H2
    A1 XX C1 XX E1 XX G1 XX
                     
    XX 01 XX 02 XX 03 XX 04
    05 XX 06 XX 07 XX 08 XX
    XX 09 XX 10 XX 11 XX 12
    13 XX 14 XX 15 XX 16 XX
    XX 17 XX 18 XX 19 XX 20
    21 XX 22 XX 23 XX 24 XX
    XX 25 XX 26 XX 27 XX 28
    29 XX 30 XX 31 XX 32 XX            
    */
       
}
