package business;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class Visit {
    
    public static Player newVisitor() {
        Visitor visitor = new Visitor();
        PersistenceTransaction.instance().create(visitor);    
        Player player = new Player();              
        player.setUsername(Player.class.getSimpleName() + visitor.getId());
        player.setId(player.getUsername().toLowerCase());
        player.setPassword("QWERTYUIOPLKJHGFDSAZXCVBNM");
        PersistenceTransaction.instance().create(player);        
        return player;       
    }
    
}
