package business;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class Contact {
    
    private static final Contact instance = new Contact();

    public static synchronized Contact instance() {
        return instance;
    }

    private Contact() {

    }
    
    public void process(String secret_identifier, String action, String target) {
        Player player = Players.getPlayer(secret_identifier);        
        if (action.equalsIgnoreCase("Save")){           
            player.getContact().add(target);
            PersistenceTransaction.instance().update(player);
        } else if(action.equalsIgnoreCase("Unsave")){           
            player.getContact().remove(target);            
            PersistenceTransaction.instance().update(player);
        } else if (action.equalsIgnoreCase("Block")){            
            player.getBlock().add(target);
            PersistenceTransaction.instance().update(player);
        } else if (action.equalsIgnoreCase("Unblock")){            
            player.getBlock().remove(target);
            PersistenceTransaction.instance().update(player);
        }
        Players.add(player);
    }
    
}
