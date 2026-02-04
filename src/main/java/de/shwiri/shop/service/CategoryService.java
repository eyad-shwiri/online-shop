package de.shwiri.shop.service;

import de.shwiri.shop.model.Category;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryService {

    @PersistenceContext(unitName = "ShopPU")
    private EntityManager em;

    public void createCategory(Category category) {
        em.persist(category);
    }

    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }
    public Category findById(Long id) {
        return em.find(Category.class, id);
    }
}
