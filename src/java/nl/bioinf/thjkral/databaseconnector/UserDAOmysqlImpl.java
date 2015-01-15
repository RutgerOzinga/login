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
 * Establishes a connection with a MySQL database and handles users in it. Can
 * connect, disconnect, log in a user and register a new user
 *
 * @author Tom Kral
 * @author Rutger Ozinga
 */
public final class UserDAOmysqlImpl implements UserDAO {

    private Connection connection;

    /**
     * Establishes connection with database.
     *
     * @author Tom Kral
     * @param dbUrl
     * @param dbUser
     * @param dbPass
     * @throws IOException
     */
    public void connect(String dbUrl, String dbUser, String dbPass) throws IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");//loads the JDBC driver class
            try {
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);//establishes the connection
                Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.INFO, "****Connected!");
                System.out.println("***Connected");
            } catch (SQLException e) {
                Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, "****NOT Connected!");
                throw new IOException(e.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException("Could not load Driver class");
        }
    }

    /**
     * Disconnects from database.
     *
     * @author Tom Kral
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
     * @author Tom Kral
     * @param username
     * @param password
     * @return User
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
                System.out.println("User logged in!");
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
     * @author Tom Kral
     * @param username
     * @param firstName
     * @param lastName
     * @param emailAdress
     * @param password
     * @return User
     * @throws IOException
     */
    @Override
    public User registerUser(String username, String firstName, String lastName, String emailAdress, String password) throws IOException {

        try {
            String insertQuery = "INSERT INTO Users (username, firstname, lastname, password, email) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement psi = connection.prepareStatement(insertQuery);
            psi.setString(1, username);
            psi.setString(2, firstName);
            psi.setString(3, lastName);
            psi.setString(4, password);
            psi.setString(5, emailAdress);
            psi.executeUpdate();
            System.out.println("user registerd");
            return new User(username, firstName, lastName, emailAdress);            

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    /**
     * Checks if the given username already exists or not.
     *
     * @author Rutger Ozinga
     * @param username
     * @return boolean userExists
     * @throws IOException
     */
    @Override
    public boolean checkUsername(String username) throws IOException {
        boolean userExists = false; //false = nonexisting user, true - existing user
        String checkUsername = "SELECT username FROM Users WHERE username = ?";

        try {
            PreparedStatement checkUser = connection.prepareStatement(checkUsername);
            checkUser.setString(1, username);
            ResultSet rs = checkUser.executeQuery();

            if (!rs.next()) {
                return userExists;
            } else {
                do {
                    userExists = true;
                } while (rs.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        userExists = true;
        return userExists;
    }

    /**
     * Checks if the given emailadress already exists or not.
     *
     * @author Rutger Ozinga
     * @param email
     * @return boolean emailExists
     * @throws IOException
     */
    @Override
    public boolean checkEmail(String email) throws IOException {
        boolean emailExists = false; //false = nonexisting email, true - existing email
        String checkEmail = "SELECT email FROM Users WHERE email = ?";
        try {
            PreparedStatement checkMail = connection.prepareStatement(checkEmail);
            checkMail.setString(1, email);
            ResultSet rs = checkMail.executeQuery();
            if (rs.next()) {
                emailExists = true;
                return emailExists;
            } else {
                return emailExists;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        emailExists = true;
        return emailExists;

    }
    
//    public static void main(String[] args) {
//        
//        UserDAOmysqlImpl test = new UserDAOmysqlImpl();
//        
//        String dbUrl = "jdbc:mysql://mysql.bin/Thjkral";
//        String dbUser = "thjkral";
//        String dbPass = "eikenboom";
//        
//        String username = "Tommy";
//        String firstname = "Tom";
//        String lastname = "Kral";
//        String password = "jemoeder";
//        String email = "yomomma@gmail.com";
//        
//        try {
//            test.connect(dbUrl, dbUser, dbPass);
//            test.registerUser(username, firstname, lastname, password, email);
//        } catch (IOException ex) {
//            Logger.getLogger(UserDAOmysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }

}
