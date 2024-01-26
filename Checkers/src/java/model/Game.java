/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Lucas
 */

@Entity
public class Game implements Serializable {

    @Id
    private String id;
    private String player1;
    private String player2;
    private String gameState;
    private String turn;
    private char winner = '0';
    private Message messages = new Message(40);

    public Game() {
    }

    public Game(String player1, String player2) {
        this.id = UUID.randomUUID().toString().substring(0, 25);
        this.gameState = "bbbbbbbbbbbbppppppppvvvvvvvvvvvv";
        if (new Random().nextBoolean()) {
            this.player1 = player1;
            this.player2 = player2;
        } else {
            this.player1 = player2;
            this.player2 = player1;
        }
        this.turn = this.player1;
    }

    public String updateStateDamas(String player, String movement) {
        String oldState = gameState;
        Integer position = Integer.parseInt(movement);
        if (position < gameState.length() && position >= 0) {
            if (player.equalsIgnoreCase(player1)) {
                gameState = Checkers.updateStatePlay1(gameState, position);
            } else if (player.equalsIgnoreCase(player2)) {
                gameState = Checkers.updateStatePlay2(gameState, position);
            }
        }

        if (gameState.charAt(0) == '1' || gameState.charAt(0) == '2') {
            winner = gameState.charAt(0);
            return gameState;
        }

        gameState = Checkers.checkDamas(gameState);

        if (!gameState.equalsIgnoreCase(oldState)) {//se realizou um movimento
            changeTurn();
            if (player.equalsIgnoreCase(player1)) {
                if (Checkers.isObrigatorioComerRed(oldState.toLowerCase()) && Checkers.isObrigatorioComerRed(gameState)) {
                    changeTurn();
                }
            } else {
                if (Checkers.isObrigatorioComerWhite(oldState.toLowerCase()) && Checkers.isObrigatorioComerWhite(gameState)) {
                    changeTurn();
                }
            }
        }

        if (turn.equalsIgnoreCase(player)) {
            return getGameState(player);
        }
        return gameState;
    }

    public String getGameState(String player) {
        if (winner != '0') {
            return winner + "";
        }
        if (player.equalsIgnoreCase(player1)) {
            return Checkers.tabuleiroPlayer1(turn.equalsIgnoreCase(player1), gameState);
        } else if (player.equalsIgnoreCase(player2)) {
            return Checkers.tabuleiroPlayer2(turn.equalsIgnoreCase(player2), gameState);
        }
        return null;
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public void changeTurn() {
        if (turn.equalsIgnoreCase(player1)) {
            turn = player2;
        } else {
            turn = player1;
        }
    }

    public String getSide(String playerName) {
        if (playerName.equalsIgnoreCase(player1)) {
            return "1";
        }
        return "2";
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the turn
     */
    public String getTurn() {
        return turn;
    }

    /**
     * @return the player1
     */
    public String getPlayer1() {
        return player1;
    }

    /**
     * @return the player2
     */
    public String getPlayer2() {
        return player2;
    }

    /**
     * @return the messages
     */
    public Message getMessages() {
        return messages;
    }

}
