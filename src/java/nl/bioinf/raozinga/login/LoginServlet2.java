/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.bioinf.raozinga.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.bioinf.thjkral.databaseconnector.UserDAOmysqlImpl;

/**
 *
 * @author raozinga
 */
@WebServlet(name = "LoginServlet2", urlPatterns = {"/LoginServlet2"})
public class LoginServlet2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.length() == 0 || password == null
                || password.length() == 0) {
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        } else {

            String dbUrl;
            String dbuser;
            String dbPass;

            try {
                dbUrl = getServletConfig().getInitParameter("mysql_url");
                dbuser = getServletConfig().getInitParameter("mysql_user");
                dbPass = getServletConfig().getInitParameter("mysql_pass");

                if (dbUrl == null || dbuser == null || dbPass == null) {
                    throw new ServletException("one or more db connection params is not defined!");
                }

                UserDAOmysqlImpl dbconnect  = new UserDAOmysqlImpl();
                dbconnect.connect(dbUrl, dbuser, dbPass);

                try {
                    nl.bioinf.thjkral.databaseconnector.User user = dbconnect.loginUser(username, password);

                    if (nl.bioinf.thjkral.databaseconnector.User == null) {
                        throw new IOException("User is empty");
                    } else {
                        HttpSession session = request.getSession();
                        session.setMaxInactiveInterval(10);
                        if (session.getAttribute("user") == null) {
                            session.setAttribute("user", user);
                        }
                        request.setAttribute("user", user);
                        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                        view.forward(request, response);
                    }

                } catch (IOException e) {
                    e.getMessage();
                }

//                if (username.equalsIgnoreCase(validUsername) && password.equalsIgnoreCase(validPassword)) {
//                    User user = new User(username, password);
//                    user.setLoggedin(true);
//                    HttpSession session = request.getSession();
//                    session.setMaxInactiveInterval(10);
//                    if (session.getAttribute("user") == null) {
//                        session.setAttribute("user", user);
//                    }
//                    request.setAttribute("user", user);
//                    RequestDispatcher view = request.getRequestDispatcher("index.jsp");
//                    view.forward(request, response);
//
//                } else {
//                    request.setAttribute("login_error", "Invalid username or "
//                            + "password! Please check both, and try again");
//                    RequestDispatcher view = request.getRequestDispatcher("index.jsp");
//                    view.forward(request, response);
//                }

            } catch (ServletException | IOException ex) {
                String error = "can not connect to the database; try again or contact the administrator; available info: " + ex.getMessage();
                RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
            }

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
