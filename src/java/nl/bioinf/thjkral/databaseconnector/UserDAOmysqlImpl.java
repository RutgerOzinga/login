/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.thjkral.databaseconnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom Kral
 */
public final class UserDAOmysqlImpl implements UserDAO {

    private Connection connection;
//    String dbUser = "thjkral";
//    String dbPass = "eikenboom";
//    String dbUrl = "jdbc:mysql://mysql.bin/Thjkral";
//
//    public UserDAOmysqlImpl() throws ClassNotFoundException{
//	connect();
//    }

    /**
     * Establishes connection with database.
     *
     * @param dbUrl
     * @param dbUser
     * @param dbPass
     * @throws ClassNotFoundException
     */
    public void connect(String dbUrl, String dbUser, String dbPass) throws IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");//loads the JDBC driver class
            try {
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);//establishes the connection
                System.out.println("Conected");
            } catch (SQLException e) {
                e.getMessage();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException("Could not load Driver class");
        }
    }

    /**
     * Disconnects from database.
     *
     * @throws IOException
     */
    @Override
    public void disconnect() throws IOException {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieve userdata from database to log in user.
     *
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    @Override
    public User loginUser(String username, String password) throws IOException {
        try {
            String sqlStatement = "SELECT * FROM Users WHERE username=? AND password=?";
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String emailAdress = rs.getString("email");
                return new User(username, firstName, lastName, emailAdress);
            } else {
                throw new IllegalArgumentException("Username and Password not recognized");
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new IOException(e);
        }
    }

    /**
     * Add a new user to the database.
     *
     * @param username
     * @param firstName
     * @param lastName
     * @param emailAdress
     * @param password
     * @return
     * @throws IOException
     */
    @Override
    public User registerUser(String username, String firstName, String lastName, String emailAdress, String password) throws IOException {

        try {
            String checkUsernameQuery = "SELECT username FROM Users WHERE username=?";
            PreparedStatement ps = connection.prepareStatement(checkUsernameQuery);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                throw new IllegalArgumentException("That username already exists");
            } else {
                try {
                    String checkEmailQuery = "SELECT email FROM Users WHERE email=?";
                    PreparedStatement pse = connection.prepareStatement(checkEmailQuery);
                    pse.setString(1, emailAdress);

                    ResultSet rse = pse.executeQuery();
                    if (rse.next()) {
                        throw new IllegalArgumentException("That emailadress already exists");
                    } else {

                        try {
                            String insertQuery = "INSERT INTO Users (username, firstname, lastname, password, email) VALUES (?, ?, ?, ?, ?)";

                            PreparedStatement psi = connection.prepareStatement(insertQuery);
                            psi.setString(1, username);
                            psi.setString(2, firstName);
                            psi.setString(3, lastName);
                            psi.setString(4, password);
                            psi.setString(5, emailAdress);
                            psi.executeUpdate();
                            

                        } catch (SQLException ex) {
                            Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                } catch (SQLException | IllegalArgumentException ex) {
                    Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException | IllegalArgumentException ex) {
            Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
