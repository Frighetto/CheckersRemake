/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lucas
 */
public class UserModel implements Runnable {

    private static final UserModel instance = new UserModel();

    public static synchronized UserModel instance() {
        return instance;
    }

    private UserModel() {
//        new Thread(this).start();
    }

    private static List<Player> playersOnline = Collections.synchronizedList(new ArrayList<Player>());

    public String process(HttpServletRequest request) {

        switch (request.getParameter("action")) {
            case "getPlayerState":
                return getPlayerState(request);
            case "addContact":
                return addContact(request);
            case "removeContact":
                return removeContact(request);
            case "blockPlayer":
                return blockPlayer(request);
            case "getUserPage":
                return getUserPage(request);
            default:
                return "Index.jsp";
        }

    }

    private String getPlayerState(HttpServletRequest request) {
        Player player = getPlayerOnlineByTempID(request.getParameter("tempID"));
        if (player == null) {
            request.setAttribute("returnValue", "offline");
        } else if (player.getGameId() == null) {
            request.setAttribute("returnValue", "available");
        } else {
            request.setAttribute("returnValue", "busy");
        }

        return "MessageResponser";
    }

    public void addPlayer(Player player) {
        if (!playersOnline.contains(player)) {
            playersOnline.add(player);
        }
    }

    public Player getPlayerOnlineByTempID(String tempID) {

        for (Player player : playersOnline) {
            if (player.getTempId().equalsIgnoreCase(tempID)) {
                return player;
            }
        }
        return null;
    }

    public Player getPlayerOnlineByID(String id) {
        for (Player player : playersOnline) {
            if (player.getId().equalsIgnoreCase(id)) {
                return player;
            }
        }
        return null;
    }

    public void logout(Player player) {
        if (playersOnline.remove(player)) {
            DataBase.instance().updatePlayer(player);
        }
    }

    public int getContactsOnline(Player player) {
        int cont = 0;
        for (String contact : player.getContacts()) {
            if (getPlayerOnlineByID(contact) != null) {
                cont++;
            }
        }

        return cont;
    }

    public String isOnline(String player) {
        if (getPlayerOnlineByID(player) != null) {
            return "online";
        }

        return "offline";
    }

    public String isAdded(String player, String contact) {
        Player p = getPlayerOnlineByID(player);
        if (p != null) {
            if (p.getContacts().contains(contact)) {
                return "remover";
            }
        }

        return "adicionar";
    }

    public String isBlocked(String name, ArrayList<String> blockedPlayers) {
        for (String blockedPlayer : blockedPlayers) {
            if (blockedPlayer.equalsIgnoreCase(name)) {
                return "desbloquear";
            }
        }
        return "bloquear";
    }

    private String addContact(HttpServletRequest request) {
        Player player = getPlayerOnlineByTempID(request.getParameter("tempID"));
        String contact = request.getParameter("contact");
        player.addContact(contact);
        request.setAttribute("returnValue", "success");
        return "MessageResponser";
    }

    private String getUserPage(HttpServletRequest request) {
        Player player = getPlayerOnlineByTempID(request.getParameter("tempID"));
        request.setAttribute("player", player);
        return "User.jsp";
    }

    private String removeContact(HttpServletRequest request) {
        Player player = getPlayerOnlineByTempID(request.getParameter("tempID"));
        String contact = request.getParameter("contact");
        player.removeContact(contact);
        request.setAttribute("returnValue", "success");
        return "MessageResponser";
    }

    private String addBlockedPlayer(HttpServletRequest request) {
        Player player = getPlayerOnlineByTempID(request.getParameter("tempID"));
        String contact = request.getParameter("name");
        player.addBlockedPlayer(contact);
        request.setAttribute("returnValue", "success");
        return "MessageResponser";
    }

    private String removeBlockedPlayer(HttpServletRequest request) {
        Player player = getPlayerOnlineByTempID(request.getParameter("tempID"));
        String contact = request.getParameter("name");
        player.removeBlockPlayer(contact);
        request.setAttribute("returnValue", "success");
        return "MessageResponser";
    }

    private String blockPlayer(HttpServletRequest request) {
        Player player = getPlayerOnlineByTempID(request.getParameter("tempID"));
        String name = request.getParameter("name");
        for (String blockedPlayer : player.getBlockedPlayers()) {
            if (blockedPlayer.equalsIgnoreCase(name)) {
                return removeBlockedPlayer(request);
            }
        }
        return addBlockedPlayer(request);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            for (Player player : playersOnline) {
                DataBase.instance().updatePlayer(player);
                DataBase.instance().updateGame(GameSessionModel.instance().getGame(player.getGameId()));

            }
        }
    }

}
