/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.raozinga.login;

/**
 *
 * @author raozinga
 */
public class User {
    private String username;
    private String password;
    private Boolean loggedin;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedin = false;
    
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(Boolean loggedin) {
        this.loggedin = loggedin;
    }
}
    