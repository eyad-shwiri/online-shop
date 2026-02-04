package de.shwiri.shop.controller;

import de.shwiri.shop.model.Product;
import de.shwiri.shop.service.ProductService; // Angenommener Service für DB-Zugriff
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AdminProductController implements Serializable {

    @Inject private ProductService productService;

    @Inject private LoginController loginController;

    private Product product = new Product();
    private List<Product> cachedProducts;
    private final String indexRedirect = "index?faces-redirect=true";

    @PostConstruct
    public void init() {
        try {
            if (!loginController.isLoggedIn() || !loginController.isAdmin()) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
            } else if  (loginController.isLoggedIn() && !loginController.isAdmin()) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lädt alle Produkte für die Übersichtstabelle
    public List<Product> getProducts() {
        if (cachedProducts == null) {
            cachedProducts = productService.findAll();
        }
        return cachedProducts;
    }

    // Navigiert zur Bearbeitungsseite eines Produkts
    public String edit(Product product) {
        this.product = product;
        return "edit-product?faces-redirect=true";
    }

    public String save() {
        product.setId(null);

        productService.save(product);
        return indexRedirect;
    }

    public String update() {
        productService.update(product);
        return indexRedirect;
    }

    // Löscht ein Produkt
    public String delete(Long productId) {
        productService.delete(productId);
        return indexRedirect;
    }

    // Getter & Setter für das Produkt-Objekt (für Formular-Binding)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
