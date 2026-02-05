package de.shwiri.shop.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PasswordUtils {

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @Inject
    private Logger logger;

    public String generateHash(String password) {
        return passwordHash.generate(password.toCharArray());
    }

    public boolean verifyPassword(String password, String storedHash) {
        if (storedHash == null || password == null) {
            logger.log(Level.WARNING, "Password is null");
            return false;
        }
        if (!storedHash.contains(":")) {
            logger.log(Level.WARNING, "Invalid hash provided");
            return false;
        }
        try {
            return passwordHash.verify(password.toCharArray(), storedHash);
        } catch (Exception e) {
            logger.log(Level.FINER, e.getMessage());
            return false;
        }
    }
}
