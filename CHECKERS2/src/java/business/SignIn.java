package business;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class SignIn {
    
    public static Player loadPlayer(String username, String password) throws AuthException {
        Player player = (Player) PersistenceTransaction.instance().read(Player.class, username.toLowerCase());        
        if(player.getPassword().equalsIgnoreCase(password)){                
            return player;       
        } else {
            throw new AuthException();
        }
    }
    
}
