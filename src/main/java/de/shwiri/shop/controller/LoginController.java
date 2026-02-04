package de.shwiri.shop.controller;

import de.shwiri.shop.model.User;
import de.shwiri.shop.model.enums.UserRole;
import de.shwiri.shop.service.UserService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named @SessionScoped // Wichtig: Hält den Login-Status pro User-Session
public class LoginController implements Serializable {

    @Inject private UserService userService;

    private String email;
    private String password;
    private User currentUser;

    public String login() {
        currentUser = userService.login(email, password);

        if (currentUser != null && this.isAdmin()) {
            return "/products?faces-redirect=true";
        } else if (currentUser != null) {
            return "/index?faces-redirect=true";
        } else {
            // Fehlermeldung hinzufügen
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    // NEUE METHODE: Gibt die E-Mail des angemeldeten Benutzers zurück
    public String getLoggedInUserEmail() {
        if (isLoggedIn()) {
            System.out.println("Logged in user email: " + currentUser.getEmail());
            return currentUser.getEmail();
        }
        System.out.println("Error on Login");
        return null;
    }

    // isAdmin Methode nutzt jetzt die Enum
    public boolean isAdmin() {
        return currentUser != null && currentUser.getRole() == UserRole.ADMIN;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
