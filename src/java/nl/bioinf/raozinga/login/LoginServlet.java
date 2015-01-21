/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.raozinga.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.bioinf.thjkral.databaseconnector.UserDAOmysqlImpl;
import nl.bioinf.thjkral.databaseconnector.User;

/**
 * Servlet for registering a new user
 *
 * @author Rutger Ozinga
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet {

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
        // get the parameter username from the login form
        String username = request.getParameter("username");
        // get the parameter password from the login form
        String password = request.getParameter("password");
        
        //if there is no username or password given: view the login page 
        //change index.jsp to httpResp.sendRedirect(httpReq.getRequestURI());
        if (username == null || username.length() == 0 || password == null
                || password.length() == 0) {
            //load login page
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        } else {
            //set values for the database so they can be filled
            String dbUrl;
            String dbUser;
            String dbPass;

            try {
                //get url
                dbUrl = getServletContext().getInitParameter("mysql_url");
                //get user
                dbUser = getServletContext().getInitParameter("mysql_user");
                //get password
                dbPass = getServletContext().getInitParameter("mysql_pass");
                //make it possible to connect with the mysql database
                UserDAOmysqlImpl dbconnect = new UserDAOmysqlImpl();

                try {
                    //make the actual connection with mysql database
                    dbconnect.connect(dbUrl, dbUser, dbPass);

                    try {
                        //try to make a user given the username and password provided by the user
                        User user = dbconnect.loginUser(username, password); 
                        //disconnect from database
                        dbconnect.disconnect();
                        //get the current session
                        HttpSession session = request.getSession();
                        //make the session valid for the given number of seconds (now 10)
                        session.setMaxInactiveInterval(10);
                        //if the name of the user is null (so its empty)                        
                        if (session.getAttribute("user") == null) {
                            //save the name of the user for the session
                            session.setAttribute("user", user);
                        }
                        //save the name of the user for the request
                        request.setAttribute("user", user);
                        //make a requestdispatcher element which communicates with the user
                        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                        //view the page asked by the servlet
                        view.forward(request, response);
                    //if something is going wrong, catch exception    
                    } catch (IllegalArgumentException ex) {
                        String LoginError = ex.getMessage();
                        request.setAttribute("login_error", LoginError);
                        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                        view.forward(request, response);
                    }

                } catch (ServletException | IOException ex) {
                    String errorMessage = "User could not be logged in: " + ex.getMessage();
                    request.setAttribute("error", errorMessage);
                    RequestDispatcher view = request.getRequestDispatcher("error.jsp");
                    view.forward(request, response);
                }

            } catch (IOException ex) {
                String errorMessage = "Could not connect to database: " + ex.getMessage();
                request.setAttribute("error", errorMessage);
                RequestDispatcher view = request.getRequestDispatcher("error.jsp");
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
