package de.shwiri.shop.service;

import de.shwiri.shop.model.Order;
import de.shwiri.shop.model.OrderItem;
import de.shwiri.shop.model.Product;
import de.shwiri.shop.model.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Stateless
public class OrderService {

    @PersistenceContext(unitName = "ShopPU")
    private EntityManager em;

    public Order createOrder(User user, List<OrderItem> items) {
        Order order = new Order();
        order.setUser(user);
        order.setStatus("NEU");

        BigDecimal total = BigDecimal.ZERO;

        em.persist(order);

        for (OrderItem item : items) {
            Product p = em.find(Product.class, item.getProduct().getId());

            if (p.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException("Nicht genügend Lagerbestand für: " + p.getName());
            }

            p.setStockQuantity(p.getStockQuantity() - item.getQuantity());

            item.setOrder(order);
            item.setPriceAtPurchase(p.getPrice()); // WICHTIG: Preis fixieren

            total = total.add(p.getPrice().multiply(new BigDecimal(item.getQuantity())));

            em.persist(item);
        }

        order.setTotalAmount(total);
        return em.merge(order);
    }

    public List<Order> findOrdersByUser(User user) {
        return em.createQuery("SELECT o FROM Order o WHERE o.user = :u", Order.class)
                 .setParameter("u", user)
                 .getResultList();
    }
}
