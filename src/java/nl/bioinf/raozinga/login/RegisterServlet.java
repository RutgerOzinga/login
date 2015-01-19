/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.raozinga.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.bioinf.thjkral.databaseconnector.UserDAOmysqlImpl;
import nl.bioinf.thjkral.databaseconnector.User;

/**
 * Servlet for logging in an user
 *
 * @author Rutger Ozinga
 * @author Tom Kral
 */
public class RegisterServlet extends HttpServlet {

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

        String errormessage = "";

        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String email = request.getParameter("email");

        if (username == null || username.length() == 0
                || password == null || password.length() == 0
                || firstname == null || firstname.length() == 0
                || lastname == null || lastname.length() == 0
                || email == null || email.length() == 0) { //check if fields are made and filled

            errormessage = "Vul aub ale velden in";
            request.setAttribute("register_error", errormessage);
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);

        } else {//if fields are OK

            if (username.length() > 3 || password.length() > 5) {//username must be 3 characters or longer, password 5 or longer

                String validEmail = "[\\w]+@[\\w]+\\.[\\w]+";
                Pattern pat = Pattern.compile(validEmail);
                Matcher m = pat.matcher(email);
                while (!m.find()) {
                    errormessage = "Het emailadres is niet valide";
                    request.setAttribute("register_error", errormessage);
                    RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                    view.forward(request, response);
                }

                String dbUrl;
                String dbUser;
                String dbPass;

                try {

                    dbUrl = getServletContext().getInitParameter("mysql_url");
                    dbUser = getServletContext().getInitParameter("mysql_user");
                    dbPass = getServletContext().getInitParameter("mysql_pass");

                    UserDAOmysqlImpl dbconnect = new UserDAOmysqlImpl();

                    try {
                        dbconnect.connect(dbUrl, dbUser, dbPass);

                        boolean userExists = dbconnect.checkUsername(username);
                        boolean emailExists = dbconnect.checkUsername(email);

                        if (userExists == true) { //check if username exists in database
                            errormessage = "Deze username bestaat al";
                            request.setAttribute("register_error", errormessage);
                            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                            view.forward(request, response);
                            dbconnect.disconnect();
                        } else {
                                                                
                            if (emailExists == true) { //check if username exists in database
                                errormessage = "Dit emailadres bestaat al";
                                request.setAttribute("register_error", errormessage);
                                RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                                view.forward(request, response);                                
                            } else {

                                //register new user with valid credentials
                                User user = dbconnect.registerUser(username, firstname, lastname, email, password);

                                if (user != null) {//when registering user was a succes
                                    errormessage = "Registratie succesvol";
                                    request.setAttribute("register_error", errormessage);
                                    RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                                    view.forward(request, response);
                                    dbconnect.disconnect();
                                } else {
                                    errormessage = "Er kon geen nieuwe gebruiker aangemaakt worden";
                                    request.setAttribute("register_error", errormessage);
                                    RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                                    view.forward(request, response);
                                    dbconnect.disconnect();                                    
                                    
                                }
                            }
                        }

                    } catch (Exception ex) {
                        String errorMessage = "User could not be registered: " + ex.getMessage();
                        request.setAttribute("error", errorMessage);
                        RequestDispatcher view = request.getRequestDispatcher("error.jsp");
                        view.forward(request, response);
                    }

                } catch (Exception ex) {
                    String errorMessage = "Could not connect to database: " + ex.getMessage();
                    request.setAttribute("error", errorMessage);
                    RequestDispatcher view = request.getRequestDispatcher("error.jsp");
                    view.forward(request, response);
                }

            } else {//if username and password are too short
                errormessage = "Username moet meer dan 3 karakters bevatten en het wachtword moet langer dan 5 tekens zijn";
                request.setAttribute("register_error", errormessage);
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
