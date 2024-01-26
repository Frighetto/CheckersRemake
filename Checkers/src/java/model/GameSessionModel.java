/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lucas
 */
public class GameSessionModel {

    private static final GameSessionModel instance = new GameSessionModel();

    public static synchronized GameSessionModel instance() {
        return instance;
    }

    private Map<String, Game> games = Collections.synchronizedMap(new HashMap<String, Game>());

    public String process(HttpServletRequest request) {

        switch (request.getParameter("action")) {
            case "exitGame":
                return exitGame(request);
            case "getGame":
                return getGame(request);
            case "getChat":
                return getChat(request);
            case "getGameSession":
                return getGameSession(request);
            case "getGameState":
                return getGameState(request);
            case "updateGameState":
                return updateGameState(request);
            case "login":
                return login(request);
            default:
                return "Index.jsp";
        }

    }

    public void addNewGame(Player player1, Player player2) {
        Game game = new Game(player1.getId(), player2.getId());
        games.put(game.getId(), game);
        DataBase.instance().saveGame(game);
        player1.setGameId(game.getId());
        player2.setGameId(game.getId());
    }

    public Game getGame(String id) {
        return games.get(id);
    }

    public String getGameState(String id, String player) {
        return games.get(id).getGameState(player);
    }

    private void updateGame(String id, String player, String movement) {
        games.get(id).updateStateDamas(player, movement);
    }

    private void endGame(String id) {
        Game g = games.remove(id);
        if (g != null) {
            Player p1 = UserModel.instance().getPlayerOnlineByID(g.getPlayer1());
            if (p1 == null) {
                DataBase.instance().getPlayer(g.getPlayer1()).setGameId(null);
            }
            Player p2 = UserModel.instance().getPlayerOnlineByID(g.getPlayer2());
            if (p1 == null) {
                DataBase.instance().getPlayer(g.getPlayer2()).setGameId(null);
            }
        }

    }

    private String login(HttpServletRequest request) {
        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getAttribute("tempID").toString());
        if (player == null) {
            request.setAttribute("returnValue", "notOnline");
            return "MessageController";
        }
        Game game = games.get(player.getGameId());
        if (game == null) {
            game = DataBase.instance().getGame(player.getGameId());
            if (game == null) {
                player.setGameId(null);
                return "lobby.jsp";
            }
        }
        request.setAttribute("game", game);
        request.setAttribute("player", player);
        return "GameSession.jsp";
    }

    private String getGame(HttpServletRequest request) {
        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getParameter("tempID"));

        if (player == null) {
            request.setAttribute("returnValue", "notOnline");
            return "MessageController";
        }
        request.setAttribute("game", games.get(player.getGameId()));
        request.setAttribute("player", player);
        return "Game.jsp";
    }

    private String getGameSession(HttpServletRequest request) {
        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getParameter("tempID"));

        if (player == null) {
            return "Index.jsp";
        }

        request.setAttribute("player", player);
        return "GameSession.jsp";
    }

    private String getChat(HttpServletRequest request) {
        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getParameter("tempID"));
        request.setAttribute("player", player);
        return "GameSessionChat.jsp";
    }

    private String exitGame(HttpServletRequest request) {
        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getParameter("tempID"));
        player.setGameId(null);
        request.setAttribute("player", player);
        return "LobbyController";
    }

    void addGame(Game game) {
        games.put(game.getId(), game);
    }

    private String getGameState(HttpServletRequest request) {
        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getParameter("tempID"));
        Game game = games.get(player.getGameId());
        request.setAttribute("returnValue", game.getGameState(player.getId()));
        return "MessageResponser";
    }

    private String updateGameState(HttpServletRequest request) {
        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getParameter("tempID"));
        Game game = games.get(player.getGameId());
        request.setAttribute("returnValue", game.updateStateDamas(player.getId(), request.getParameter("mov")));
        return "MessageResponser";
    }

}
