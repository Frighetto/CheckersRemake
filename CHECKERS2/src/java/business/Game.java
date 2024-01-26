package business;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Lucas Fernando Frighetto
 */
@Entity
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private final String game_identifier;
    private final Player player1;
    private final Player player2;
    private final Checkers checkers;    

    public Game(Player player1, Player player2) {
        this.game_identifier = UUID.randomUUID().toString();
        this.player1 = player1;
        this.player2 = player2;
        this.checkers = new Checkers();
    }        

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGame_identifier() {
        return game_identifier;
    }    

    public Checkers getCheckers() {
        return checkers;
    }
    
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }         

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }       
    
}
