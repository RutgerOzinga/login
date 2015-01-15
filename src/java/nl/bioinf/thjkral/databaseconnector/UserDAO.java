/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.thjkral.databaseconnector;

import java.io.IOException;

/**
 *
 * @author Tom Kral
 */
public interface UserDAO {

    User loginUser (String username, String password) throws IOException;

    User registerUser (String username,
            String firstName,
            String lastName,
            String emailAdress,
            String password) throws IOException;

    void disconnect() throws IOException;
    
    boolean checkUsername (String username) throws IOException;
    
    boolean checkEmail (String email) throws IOException;

}
