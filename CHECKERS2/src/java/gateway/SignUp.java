package gateway;

import business.AuthException;
import business.Players;
import business.UsernameException;
import business.UsernameReservedWordException;
import java.io.IOException;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas Fernando Frighetto
 */
public class SignUp extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password_check = request.getParameter("password_check");                
        String forwardName = Lobby.class.getSimpleName();
        try {
            request.setAttribute("secret_identifier", Players.add(business.SignUp.newPlayer(username, password, password_check)));                    
        } catch (UsernameException e) {
            request.setAttribute("alert", "O nome de usuário não pode conter caracteres especiais, apenas letras e números.");
            forwardName = "error.jsp";
        } catch (UsernameReservedWordException e) {
            request.setAttribute("alert", "O nome de usuário não pode conter as palavras Player ou Vencível.");
            forwardName = "error.jsp";
        } catch(AuthException e){
            request.setAttribute("alert", "A senha deve ser igual a senha de verificação.");
            forwardName = "error.jsp";
        } catch(PersistenceException e){
            request.setAttribute("alert", "O usuário " + username + " já existe!");
            forwardName = "error.jsp";
        } finally {            
            request.getRequestDispatcher(forwardName).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
