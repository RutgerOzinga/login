/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.thjkral.databaseconnector;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom Kral
 */
public class DAOfactory {

    public static UserDAO getLoginDAO(String dbType, String dbUser, String dbPass, String dbUrl) throws IOException {
        if (dbType.equalsIgnoreCase("MySQL")) {
            return createMysqlDAO(dbType, dbUser, dbPass, dbUrl);
        }
        else {
            throw new IllegalArgumentException("Databasetype " + dbType + " is not supported");
        }
    }

    private static final UserDAO createMysqlDAO(String dbType, String dbUser, String dbPass, String dbUrl) throws IOException {

        try {
            UserDAOmysqlImpl dao = new UserDAOmysqlImpl();
            dao.connect(dbUser, dbPass, dbUrl);
            return dao;
        }
        catch (IOException e) {
            Logger.getLogger(DAOfactory.class.getName()).log(Level.SEVERE, null, e);
            throw new IOException("", e);
        }

    }

}
