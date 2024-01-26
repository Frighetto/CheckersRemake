/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lucas
 */
public class LobbyModel {

    private static final LobbyModel instance = new LobbyModel();

    public static synchronized LobbyModel instance() {
        return instance;
    }

    /**
     * @return the challenges
     */
    public static Map<String, ArrayList<String>> getChallenges() {
        return challenges;
    }

    /**
     * @return the messages
     */
    public static Message getMessages() {
        return messages;
    }

    private LobbyModel() {

    }

    private static Message messages = new Message(40);
    private static List<Player> players = Collections.synchronizedList(new ArrayList<Player>());
    private static Map<String, ArrayList<String>> challenges = Collections.synchronizedMap(new HashMap<String, ArrayList<String>>());


    //método para processar o request
    public String process(HttpServletRequest request) {

        switch (request.getParameter("action")) {
            case "createAccount":
                return login(request);
            case "login":
                return login(request);
            case "visitor":
                return login(request);
            case "exitGame":
                return login(request);
            case "getChat":
                return getChat(request);
            case "getPlayers":
                return getPlayers(request);
            case "singleChallenge":
                return singleChallenge(request);
            case "cancelSingleChallenge":
                return cancelSingleChallenge(request);
            case "challengeBySkill":
                return challengeBySkill(request);
            case "cancelChallengesBySkill":
                return cancelChallengesBySkill(request);
            case "challengeAll":
                return challengeAll(request);
            case "cancelAllChallenges":
                return cancelAllChallenges(request);
            case "exit":
                return exit(request);
            default:
                return "Index.jsp";
        }

    }

    private String singleChallenge(HttpServletRequest request) {
        String singleChallenge = request.getParameter("singleChallenge");
        Player player1 = getPlayerInLobbyByTempID(request.getParameter("tempID"));
        if (getChallenges().containsKey(singleChallenge)) {
            if (getChallenges().get(singleChallenge).contains(player1.getId())) {
                Player player2 = getPlayerInLobbyByID(singleChallenge);
                return startNewGame(request, player1, player2);
            }
        }
        if (!challenges.containsKey(player1.getId())) {
            getChallenges().put(player1.getId(), new ArrayList<String>());
        }
        if (!challenges.get(player1.getId()).contains(singleChallenge)) {
            getChallenges().get(player1.getId()).add(singleChallenge);
        }
        request.setAttribute("returnValue", "challenged");
        return "MessageController";
    }

    private String cancelSingleChallenge(HttpServletRequest request) {
        String cancelSingleChallenge = request.getParameter("singleChallenge");
        Player player = getPlayerInLobbyByTempID(request.getParameter("tempID"));
        if (player != null) {
            getChallenges().get(player.getId()).remove(cancelSingleChallenge);
        }
        request.setAttribute("returnValue", "success");
        return "MessageController";
    }

    private String challengeBySkill(HttpServletRequest request) {
        boolean canStartNewGame = false;
        String skill = request.getParameter("skill");
        Player player1 = getPlayerInLobbyByTempID(request.getParameter("tempID"));
        Player player2 = null;
        for (Entry<String, ArrayList<String>> set : getChallenges().entrySet()) {
            player2 = getPlayerInLobbyByID(set.getKey());
            if (player2.getSkill().equalsIgnoreCase(skill) && set.getValue().contains(player1.getId())) {
                canStartNewGame = true;
                break;
            }
        }
        if (canStartNewGame) {
            return startNewGame(request, player1, player2);
        }
        if (!challenges.containsKey(player1.getId())) {
            getChallenges().put(player1.getId(), new ArrayList<String>());
        }
        for (Player player : players) {
            if (player.getSkill().equalsIgnoreCase(skill) && !challenges.get(player1.getId()).contains(player.getId()) && !player1.getId().equalsIgnoreCase(player.getId())) {
                getChallenges().get(player1.getId()).add(player.getId());
            }
        }
        request.setAttribute("returnValue", "challenged");
        return "MessageController";
    }

    private String cancelChallengesBySkill(HttpServletRequest request) {
        String cancelChallengesBySkill = request.getParameter("skill");
        Player player = getPlayerInLobbyByTempID(request.getParameter("tempID"));
        Player p = null;
        for (int i = getChallenges().get(player.getId()).size() - 1; i >= 0; i--) {
            p = getPlayerInLobbyByID(getChallenges().get(player.getId()).get(i));
            if (p.getSkill().equalsIgnoreCase(cancelChallengesBySkill)) {
                getChallenges().get(player.getId()).remove(i);
            }
        }
        request.setAttribute("returnValue", "success");
        return "MessageController";
    }

    private String challengeAll(HttpServletRequest request) {
        String challenge = null;
        Player player1 = getPlayerInLobbyByTempID(request.getParameter("tempID"));
        for (Entry<String, ArrayList<String>> set : getChallenges().entrySet()) {
            if (set.getValue().contains(player1.getId())) {
                challenge = set.getKey();
                break;
            }
        }
        if (challenge != null) {
            Player player2 = getPlayerInLobbyByID(challenge);
            return startNewGame(request, player1, player2);
        }
        if (!challenges.containsKey(player1.getId())) {
            getChallenges().put(player1.getId(), new ArrayList<String>());
        }
        for (Player player : players) {
            if (!challenges.get(player1.getId()).contains(player.getId()) && !player1.getId().equalsIgnoreCase(player.getId())) {
                getChallenges().get(player1.getId()).add(player.getId());
            }
        }
        request.setAttribute("returnValue", "challenged");
        return "MessageController";
    }

    private String cancelAllChallenges(HttpServletRequest request) {
        Player player = getPlayerInLobbyByTempID(request.getParameter("tempID"));
        if (player != null) {
            getChallenges().remove(player.getId());
        }
        request.setAttribute("returnValue", "challenged");
        return "MessageController";
    }

    private String startNewGame(HttpServletRequest request, Player player1, Player player2) {
        getChallenges().remove(player1.getId());
        getChallenges().remove(player2.getId());
        removePlayer(player1);
        removePlayer(player2);

        GameSessionModel.instance().addNewGame(player1, player2);

        request.setAttribute("returnValue", "success");
        return "MessageController";
    }

    private String setLobby(HttpServletRequest request) {
        Player player = (Player) request.getAttribute("player");
        return "Lobby.jsp";
    }

    private String login(HttpServletRequest request) {
        Player player = (Player) request.getAttribute("player");
        addPlayer(player);
        return setLobby(request);
    }

    private String exit(HttpServletRequest request) {
        Player player = getPlayerInLobbyByTempID(request.getParameter("tempID"));
        getChallenges().remove(player.getId());
        removePlayer(player);
        UserModel.instance().logout(player);
        return "Index.jsp";
    }

    private String getChat(HttpServletRequest request) {
        Player player = getPlayerInLobbyByTempID(request.getParameter("tempID"));
        request.setAttribute("player", player);
        return "LobbyChat.jsp";
    }

    private String getPlayers(HttpServletRequest request) {
        Player player = getPlayerInLobbyByTempID(request.getParameter("tempID"));
        if (player == null) {
            request.setAttribute("returnValue", "notInLobby");
            return "MessageController";
        }

        request.setAttribute("player", player);
        return "LobbyPlayers.jsp";
    }


    private void addPlayer(Player player) {
        if (!containsPlayer(player)) {
            players.add(player);
        }
    }

    private void removePlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId().equalsIgnoreCase(player.getId())) {
                players.remove(i);
                break;
            }
        }
    }

    private boolean containsPlayer(Player player) {
        for (Player p : players) {
            if (p.getId().equalsIgnoreCase(player.getId())) {
                return true;
            }
        }
        return false;
    }

    private Player getPlayerInLobbyByTempID(String tempID) {
        for (Player player : players) {
            if (player.getTempId().equalsIgnoreCase(tempID)) {
                return player;
            }
        }
        return null;
    }

    private Player getPlayerInLobbyByID(String id) {
        for (Player player : players) {
            if (player.getId().equalsIgnoreCase(id)) {
                return player;
            }
        }
        return null;
    }

    public ArrayList<Player> getOtherPlayers(String player) {
        ArrayList<Player> otherPlayers = new ArrayList<>(players);
        for (int i = 0; i < otherPlayers.size(); i++) {
            if (otherPlayers.get(i).getId().equalsIgnoreCase(player)) {
                otherPlayers.remove(i);
                break;
            }
        }
        return otherPlayers;
    }

    public String getChallengeSituation(String player, String challenger) {
        if (challenges.containsKey(challenger)) {
            if (challenges.get(challenger).contains(player)) {
                return "iniciar";
            }
        }

        if (challenges.containsKey(player)) {
            if (challenges.get(player).contains(challenger)) {
                return "cancelar";
            }
        }

        return "desafiar";
    }

}
