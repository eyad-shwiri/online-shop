package de.shwiri.shop.controller;

import de.shwiri.shop.annotations.Web;
import de.shwiri.shop.model.User;
import de.shwiri.shop.model.enums.Redirection;
import de.shwiri.shop.model.enums.UserRole;
import de.shwiri.shop.service.UserService;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Web
public class LoginController implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private Logger logger;

    private String email;
    private String password;
    private User currentUser;

    public String checkAdminAccess() {
        if (!isAdmin()) {
            return Redirection.INDEX.getRedirect();
        }
        logger.log(Level.FINER,"Current user {0} tried to access admin page", currentUser.getEmail());
        return null;
    }

    public String login() {
        currentUser = userService.login(email, password);

        if (currentUser != null && this.isAdmin()) {
            return Redirection.PRODUCTS.getRedirect();
        } else if (currentUser != null) {
            return Redirection.INDEX.getRedirect();
        } else {
            // Fehlermeldung hinzufügen
            logger.log(Level.FINER,"Login Failed");
            return Redirection.ERROR.getRedirect();
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        logger.log(Level.INFO, "Logout Successful");
        return Redirection.LOGIN.getRedirect();
    }

    public String getLoggedInUserEmail() {
        if (isLoggedIn()) {
            logger.log(Level.INFO,"Logged user email: {0}", currentUser.getEmail());
            return currentUser.getEmail();
        }
        logger.log(Level.FINER, "Login Failed");
        return Redirection.ERROR.getRedirect();
    }

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
