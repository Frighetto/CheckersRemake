package business;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Lucas Fernando Frighetto
 */
@Entity
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
       
    @Id
    private String id;
    private String username;
    private String password;           
    
    private final HashSet contact = new HashSet();
    private final HashSet block = new HashSet();   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }       

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }        

    public HashSet getContact() {
        return contact;
    }

    public HashSet getBlock() {
        return block;
    }        

    @Override
    public boolean equals(Object object) {        
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        return !((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username)));
    }

    @Override
    public int hashCode() {        
        return Objects.hashCode(this.username);
    }

    @Override
    public String toString() {
        return "Player{" + username + ", password=" + password + '}';
    }  
    
}
