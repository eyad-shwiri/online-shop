package de.shwiri.shop.service;

import de.shwiri.shop.model.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductService {

    @PersistenceContext(unitName = "ShopPU")
    private EntityManager em;

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public List<Product> findByCategory(Long categoryId) {
        return em.createQuery("SELECT p FROM Product p WHERE p.category.id = :catId", Product.class).setParameter("catId", categoryId)
                 .getResultList();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public void save(Product product) {
//        product.setId(null);
        em.persist(product);
    }

    public void update(Product product) {
        em.merge(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        if (product != null) {
            em.remove(em.contains(product) ? product : em.merge(product));
        }
    }
}
