/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thandeka.matrose
 */
public class User extends Citizen{
    protected String username = "";
    private String password = "";
    protected boolean isAdmin = false;
    protected String email = "None provided";

    
    
    public User(Citizen newCitizen, String newPassword, boolean newIsAdmin, String newUsername,String newEmail){
        super(newCitizen.getId(),newCitizen.getPersonalId(),newCitizen.getFirstName(),newCitizen.getLastName(), newCitizen.getIsVaccinated(),newCitizen.getContactNumber(),newCitizen.getRiskLevelNum(),newCitizen.getHousehold());
        setUsername(newUsername);
        setPassword(newPassword);
        setIsAdmin(newIsAdmin);
        setEmail(newEmail);
    }

    public User() {
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        try {
            if(newPassword.length()>0){
                password = newPassword;
            }
        } catch (NullPointerException npe) {
            password = null;
        }
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean newIsAdmin) {
        isAdmin = newIsAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String newUsername) {
        if(newUsername.length()>0){
            username = newUsername;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        if (newEmail.length()>0) {
            email = newEmail;
        }
    }

    @Override
    public String toString() {
        return username ;
    }
    
}
