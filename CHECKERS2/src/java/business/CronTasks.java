package business;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class CronTasks {
    
    private static final CronTasks instance = new CronTasks();
    
    public static synchronized CronTasks instance(){
        return instance;
    }
    
    private CronTasks(){
        timeout_check();
    }            
    
    private void timeout_check(){
        boolean disconnected_is_loser = true;
        new Thread() {

            @Override
            public void run() {
                
                try {                                       
                    while(disconnected_is_loser){
                        Map<String, Integer> activity_copy = new HashMap<>();
                        activity_copy.putAll(Players.getActivity());
                        for (Map.Entry<String, Integer> player : activity_copy.entrySet()) {                         
                            if(player.getValue() == 100){                
                                Players.logout(player.getKey());                            
                            } else {                
                                Players.getActivity().put(player.getKey(), player.getValue() + 1);                                           
                            }           
                        }      
                        Thread.sleep(3141);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(CronTasks.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }.start();
        
    }
    
}
