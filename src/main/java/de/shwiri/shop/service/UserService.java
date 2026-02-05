package de.shwiri.shop.service;

import de.shwiri.shop.model.User;
import de.shwiri.shop.util.PasswordUtils;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "ShopPU")
    private EntityManager em;

    @Inject
    private PasswordUtils passwordUtils;

    @Inject
    private Logger logger;

    public void registerUser(User user) {
        // wird verwendet, wenn ein Benutzer sich registrieren kann dann wird hier Passwort-Hashing integriert!
        em.persist(user);
    }

    public User login(String email, String password) {
        try {
            User logedUser = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                     .setParameter("email", email)
                     .getSingleResult();
            if (passwordUtils.verifyPassword(password, logedUser.getPasswordHash())){
                return logedUser;
            }else {
                throw new NoResultException();
            }
        } catch (NoResultException e) {
            logger.log(Level.INFO, "Password is incorrect");
            logger.log(Level.FINER, e.getMessage());
            return null;
        }
    }

}
