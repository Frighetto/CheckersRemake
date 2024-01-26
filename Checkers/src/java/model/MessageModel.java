/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lucas
 */
public class MessageModel {

    private static final MessageModel instance = new MessageModel();

    public static synchronized MessageModel instance() {
        return instance;
    }

    private MessageModel() {

    }

    public synchronized String process(HttpServletRequest request) {

        switch (request.getParameter("action")) {
            case "addMessage":
                return addMessage(request);
            case "getAllMessages":
                return getAllMessages(request);
            case "seekNewMessages":
                return seekNewMessages(request);
            default:
                return "";
        }
    }

    public static boolean isNormalMessage(String message) {
        if (message.length() > 5) {
            if (message.substring(0, 1).equalsIgnoreCase("/")) {
                int messageStartIndex = message.indexOf(":", 2);
                if (messageStartIndex != -1 && messageStartIndex < 20) {
                    return false;
                }
            }
        }
        return true;
    }

    public String addMessage(HttpServletRequest request) {
        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getParameter("tempID"));
        String message = request.getParameter("message");
        String currentChat = request.getParameter("currentChat");
        String realMessage;
        if (isNormalMessage(message)) {

            realMessage = player.getId() + "[" + new Date().toLocaleString().substring(11, 19) + "]: " + message;
            if (currentChat.equalsIgnoreCase("lobby")) {
                LobbyModel.instance().getMessages().add(realMessage);
            } else if (currentChat.equalsIgnoreCase("gameSession")) {
                GameSessionModel.instance().getGame(player.getGameId()).getMessages().add(realMessage);
            }

        } else {

            String receiver = message.substring(1, message.indexOf(":"));
            Player playerReceiver;
            realMessage = player.getId() + "[" + new Date().toLocaleString().substring(11, 19) + "]: " + message.substring(message.indexOf(":") + 1);
            player.getMessage().add(realMessage);

            if (receiver.equalsIgnoreCase("friends")) {
                for (String contact : player.getContacts()) {
                    playerReceiver = UserModel.instance().getPlayerOnlineByID(contact);
                    playerReceiver.getMessage().add(realMessage);
                }
            } else {
                playerReceiver = UserModel.instance().getPlayerOnlineByID(receiver);
                playerReceiver.getMessage().add(realMessage);
            }

        }
        return seekNewMessages(request);
    }

    private String getAllMessages(HttpServletRequest request) {
        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getParameter("tempID"));
        String currentChat = request.getParameter("currentChat");
        int index = 0;
        int privateIndex = 0;

        ArrayList<String> newMessages = new ArrayList<>();
        privateIndex = player.getMessage().getAllMessages(newMessages, privateIndex);
        if (currentChat.equalsIgnoreCase("lobby")) {
            index = LobbyModel.instance().getMessages().getAllMessages(newMessages, index);
        } else if (currentChat.equalsIgnoreCase("gameSession")) {
            index = GameSessionModel.instance().getGame(player.getGameId()).getMessages().getAllMessages(newMessages, index);
        }
        ;
        request.setAttribute("returnValue", Message.prepareMessage(newMessages, privateIndex, privateIndex, player.getBlockedPlayers()));
        return "MessageResponser";
    }

    private String seekNewMessages(HttpServletRequest request) {

        Player player = UserModel.instance().getPlayerOnlineByTempID(request.getParameter("tempID"));
        int index = Integer.parseInt(request.getParameter("lastMessageIndex"));
        int privateIndex = Integer.parseInt(request.getParameter("privateLastMessageIndex"));
        String currentChat = request.getParameter("currentChat");

        ArrayList<String> newMessages = new ArrayList<>();
        privateIndex = player.getMessage().getMessageListFromIndex(newMessages, privateIndex);

        if (currentChat.equalsIgnoreCase("lobby")) {
            index = LobbyModel.instance().getMessages().getMessageListFromIndex(newMessages, index);
        } else if (currentChat.equalsIgnoreCase("gameSession")) {
            index = GameSessionModel.instance().getGame(player.getGameId()).getMessages().getMessageListFromIndex(newMessages, index);
        }

        request.setAttribute("returnValue", Message.prepareMessage(newMessages, privateIndex, index, player.getBlockedPlayers()));
        return "MessageResponser";
    }

}
