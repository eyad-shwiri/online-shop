package de.shwiri.shop.controller;

import de.shwiri.shop.model.Product;
import de.shwiri.shop.service.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProductController implements Serializable {

    @Inject
    private ProductService productService;

    private Long selectedCategoryId;
    private List<Product> allProducts;
    private Product selectedProduct;

    @PostConstruct
    public void init() {
        allProducts = productService.findAll();
    }

    // Getter
    public List<Product> getAllProducts() {
        return allProducts;
    }

    public List<Product> getFilteredProducts() {
        if (selectedCategoryId == null) {
            return allProducts;
        }

        return allProducts.stream().filter(p -> p.getCategory() != null &&
                selectedCategoryId.equals(p.getCategory().getId())).toList();
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Long getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(Long selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }
}
