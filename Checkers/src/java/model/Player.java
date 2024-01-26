/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.UUID;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Lucas
 */


@Entity
public class Player implements Serializable{
    
    @Id
    private String id;
    private final String tempId  = UUID.randomUUID().toString().substring(0, 20);     ;
    private String gameId;
    private String password;   
    private String skill;
    private ArrayList<String> contacts = new ArrayList<>();
    private ArrayList<String> blockedPlayers = new ArrayList<>();
    private Message message = new Message(40);

    public Player() {               
    }        

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the gameId
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the tempId
     */
    public String getTempId() {
        return tempId;
    }   

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }   

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
  
    /**
     * @return the skill
     */
    public String getSkill() {
        return skill;
    }

    /**
     * @param skill the skill to set
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }

    /**
     * @return the contacts
     */
    public List<String> getContacts() {
        return contacts;
    }
    
    public void addContact(String contact){
        contacts.add(contact);        
    }

    public void removeContact(String contact) {
        contacts.remove(contact);
    }
    
    public void addBlockedPlayer(String name){
        for(int i=0; i < contacts.size();i++){
            if(contacts.get(i).equalsIgnoreCase(name)){
                contacts.remove(i);
                break;
            }
        }        
        getBlockedPlayers().add(name);
    }
    
    public void removeBlockPlayer(String name){
        getBlockedPlayers().remove(name);
    }
   
    /**
     * @return the message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * @return the blockedPlayers
     */
    public ArrayList<String> getBlockedPlayers() {
        return blockedPlayers;
    }
    
     @Override
    public String toString() {
        return "Player{" + "id=" + id + ", tempId=" + tempId + ", gameId=" + gameId + ", password=" + password + ", contacts=" + getContacts() + '}';
    }

    
}
