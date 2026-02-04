package de.shwiri.shop.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

@ApplicationScoped
public class PasswordUtils {

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    public String generateHash(String password) {
        // KEIN initialize mehr nötig!
        return passwordHash.generate(password.toCharArray());
    }

    public boolean verifyPassword(String password, String storedHash) {
        if (storedHash == null || password == null) return false;

        // Falls du noch alte SHA-256 Hashes hast, die keine Doppelpunkte enthalten:
        if (!storedHash.contains(":")) {
            return false; // Oder hier Logik für alten SHA-256 Check einbauen
        }

        try {
            // Nutze den injizierten Hash ohne manuelle Initialisierung
            return passwordHash.verify(password.toCharArray(), storedHash);
        } catch (Exception e) {
            return false;
        }
    }


}
