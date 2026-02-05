package de.shwiri.shop.controller;

import de.shwiri.shop.annotations.Web;
import de.shwiri.shop.model.Product;
import de.shwiri.shop.model.OrderItem;
import de.shwiri.shop.model.enums.Redirection;
import de.shwiri.shop.service.OrderService;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Web
public class CartController implements Serializable {

    @Inject
    private OrderService orderService;

    @Inject
    private LoginController loginController;

    private final Map<Long, OrderItem> cartItems = new HashMap<>();

    public void addToCart(Product product) {
        if (cartItems.containsKey(product.getId())) {
            OrderItem item = cartItems.get(product.getId());
            item.setQuantity(item.getQuantity() + 1);
        } else {
            OrderItem newItem = new OrderItem();
            newItem.setProduct(product);
            newItem.setQuantity(1);
            cartItems.put(product.getId(), newItem);
        }
    }

    public void removeFromCart(Long productId) {
        cartItems.remove(productId);
    }

    public BigDecimal getTotal() {
        return cartItems.values().stream().map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String checkout() {
        if (!loginController.isLoggedIn()) {
            return Redirection.LOGIN.getRedirect();
        }

        if (cartItems.isEmpty()) {
            return null;
        }

        // Bestellung über den Service abschließen
        orderService.createOrder(loginController.getCurrentUser(), new ArrayList<>(cartItems.values()));

        // Warenkorb nach Erfolg leeren
        cartItems.clear();

        return Redirection.ORDER_SUCCESS.getRedirect();
    }

    // Getter für die View
    public List<OrderItem> getItems() {
        return new ArrayList<>(cartItems.values());
    }

    public int getCartSize() {
        return cartItems.values().stream().mapToInt(OrderItem::getQuantity).sum();
    }
}
