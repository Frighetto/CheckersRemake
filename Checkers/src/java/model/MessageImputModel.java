/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lucas
 */
public class MessageImputModel {

    private static final MessageImputModel instance = new MessageImputModel();

    public static synchronized MessageImputModel instance() {
        return instance;
    }

    private MessageImputModel() {

    }

    public String process(HttpServletRequest request) {
        switch (request.getParameter("action")) {
            case "addMessage":
                return addMessage(request);
            case "getImputMessage":
                return getImputMessage(request);
            default:
                return null;
        }

    }

    private String addMessage(HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message.length() > 5) {
            if (message.substring(0, 2).equalsIgnoreCase("/'")) {
                int messageStartIndex = message.indexOf("':", 2);
                if (messageStartIndex != -1) {
                    messageStartIndex += 2;
                    String destiny = message.substring(2, messageStartIndex);
                    if (destiny != null) {
                        if (destiny.equalsIgnoreCase("friends") || DataBase.instance().getPlayer(destiny) != null) {
                            request.setAttribute("destiny", destiny);
                            request.setAttribute("message", message.substring(messageStartIndex));
                            return "UserModel";
                        }
                    }
                }
            }
        }
        return sendToChat(request);
    }

    private String sendToChat(HttpServletRequest request) {
        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getParameter("tempID"));
        if (player != null) {
            if (player.getGameId() == null) {
                return "LobbyController";
            } else {
                return "GameSessionController";
            }
        }
        return null;
    }

    private String getImputMessage(HttpServletRequest request) {
        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getParameter("tempID"));
        request.setAttribute("player", player);
        return "ImputMessage.jsp";
    }
}
