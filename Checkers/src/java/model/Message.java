/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Lucas
 */
public class Message implements Serializable {

   

    private HashMap<Integer, String> messages = new HashMap<>();
    private int index;
    private final int limit;

    public Message(int limit) {
        this.limit = limit;
        this.index = -1;
    }

    public void add(String message) {
        index++;
        messages.put(index, message);
        if (messages.size() == limit) {
            messages.remove(index - limit);
        }
    }

    public int getAllMessages(ArrayList<String> messages, int index) {
        if (!this.messages.isEmpty()) {
            for (int i = index; i <= this.index; i++) {
                messages.add(this.messages.get(i));
            }
        }
        return this.index;
    }

    public int getMessageListFromIndex(ArrayList<String> messages, int index) {
        for (int i = index + 1; i <= this.index; i++) {
            messages.add(this.messages.get(i));
        }
        return this.index;
    }

    public static String prepareMessage(ArrayList<String> messages, int privateMessageIndex, int messageIndex, ArrayList<String> blockList) {
        StringBuilder sb = new StringBuilder();
        sb.append(privateMessageIndex + "$" + messageIndex + "#");
        for (String message : messages) {
            String name = message.substring(0, message.indexOf("["));
            if (!isBlocked(blockList, name)) {
                sb.append(message);
            }
            sb.append("\n");
        }
        if (!messages.isEmpty()) {
            return sb.toString().substring(0, sb.toString().length() - 1);
        }
        return sb.toString();
    }
    
     private static boolean isBlocked(ArrayList<String> blockList, String name) {
        for(String blockedPlayer: blockList){
            if(blockedPlayer.equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
}
