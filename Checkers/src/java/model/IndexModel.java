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
public class IndexModel {

    public static String process(HttpServletRequest request) {
        String action = request.getParameter("action");
        request.setAttribute("feedback", "");
        if(action == null){
            return "Index.jsp";
        }
        switch(action){
            case "login":
                return login(request);                
            case "createAccount":
                return createAccount(request);                
            case "visitor":
                return visitor(request);                
            default:
                return "Index.jsp";                            
        }      
    }

    private static String login(HttpServletRequest request) {
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");        

        Player player = DataBase.instance().getPlayer(id);        
        
        UserModel.instance().addPlayer(player);

        if (player == null || !player.getPassword().equalsIgnoreCase(pass)) {
            request.setAttribute("feedback", "Usuário ou senha inválidos");
            return "Index.jsp";
        }
        request.setAttribute("player", player);
        
        if (player.getGameId() != null) {   
            request.setAttribute("tempID", player.getTempId());
            return "GameSessionController";
        }

        return "LobbyController";
    }

    private static String createAccount(HttpServletRequest request) {
        String id = request.getParameter("new_account");
        String pass = request.getParameter("new_pass");
        String confirmPass = request.getParameter("confirm_pass");
        
        if(id.compareToIgnoreCase("Visitante") == 0){
            request.setAttribute("feedback", "O nome de usuário não pode conter a palavra 'visitante'");
            return "Index.jsp";
        }

        if (id.length() < 3 || id.length() > 15) {
            request.setAttribute("feedback", "O nome de usuáio deve conter entre 3 e 15 caracteres");
            return "Index.jsp";
        }

        if (pass.length() < 6 || pass.length() > 20) {
            request.setAttribute("feedback", "A senha deve conter entre 6 e 20 caracteres");
            return "Index.jsp";
        }

        if (DataBase.instance().getPlayer(id) != null) {
            request.setAttribute("feedback", "O nome de usuáio " + id + " já existe");
            return "Index.jsp";
        }

        if (!pass.equalsIgnoreCase(confirmPass)) {
            request.setAttribute("feedback", "A confirmação da senha falhou");
            return "Index.jsp";
        }
        Player player = new Player();
        player.setId(id);
        player.setPassword(pass);
        player.setSkill("beginner");
        DataBase.instance().savePlayer(player);
        
        UserModel.instance().addPlayer(player);
        
        request.setAttribute("player", player);        
        return "LobbyController";
    }

    private static String visitor(HttpServletRequest request) {
        Player player = new Player();
        player.setId("Visitante"+DataBase.instance().countPlayers());  
        player.setSkill("beginner");
        DataBase.instance().savePlayer(player);
        request.setAttribute("player", player);
        
        UserModel.instance().addPlayer(player);
        
        return "LobbyController";
    }
    
}
