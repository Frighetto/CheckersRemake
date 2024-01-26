package business;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class SignUp {

    public static Player newPlayer(String username, String password, String password_check) throws AuthException, UsernameException, UsernameReservedWordException {
        if (non_reserved_username(username)) {
            if (valid_username(username)) {
                if (password.equalsIgnoreCase(password_check)) {
                    Player player = new Player();
                    player.setUsername(username);
                    player.setId(player.getUsername().toLowerCase());
                    player.setPassword(password);
                    PersistenceTransaction.instance().create(player);
                    return player;
                } else {
                    throw new AuthException();
                }
            } else {
                throw new UsernameException();
            }
        } else {
            throw new UsernameReservedWordException();
        }
    }

    private static Boolean valid_username(String username) {
        String valid_characters = "0123456789 QWERTYUIOPASDFGHJKLZXCVBNM qwertyuiopasdfghjklzxcvbnm";
        for(int i = 0; i < username.length(); i = i + 1){
            if(!valid_characters.contains(username.charAt(i) + "")){
                return false;
            }
        }
        if(username.startsWith(" ") || username.endsWith(" ") || username.contains("  ")){
            return false;
        }             
        return true;
    }

    private static Boolean non_reserved_username(String username) {
        if (username.toLowerCase().contains("player") || username.toLowerCase().contains("vencível") || username.toLowerCase().contains("vencivel")) {
            return false;
        }
        return true;
    }

}
