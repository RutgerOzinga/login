/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.thjkral.databaseconnector;

/**
 *
 * @author Tom Kral
 */
public class User {

    private String username;
    private String firstName;
    private String lastName;
    private String emailAdress;


    public User(String username, String firstName, String lastName, String emailAdress) {
        this.username = username;
        this.firstName = firstName;
        this.emailAdress = emailAdress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }


}
