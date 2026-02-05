package de.shwiri.shop.controller;

import de.shwiri.shop.annotations.Web;
import de.shwiri.shop.model.Category;
import de.shwiri.shop.model.Product;
import de.shwiri.shop.model.enums.Redirection;
import de.shwiri.shop.service.CategoryService;
import de.shwiri.shop.service.ProductService;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Web
public class AdminProductController implements Serializable {

    @Inject
    private ProductService productService;

    @Inject
    private CategoryService categoryService;

    private Product product = new Product();
    private Product newProduct = new Product();
    private List<Product> cachedProducts;
    private Category category = new Category();
    private Category newCategory = new Category();
    private List<Category> cachedCategories;

    public List<Product> getProducts() {
        if (cachedProducts == null) {
            cachedProducts = productService.findAll();
        }
        return cachedProducts;
    }

    public List<Category> getCategories() {
        if (cachedCategories == null) {
            cachedCategories = categoryService.findAll();
        }
        return cachedCategories;
    }

    public String edit(Product product) {
        this.product = product;
        return Redirection.EDIT_PRODUCT.getRedirect();
    }

    public String save() {
        productService.save(newProduct);
        this.newProduct = new Product();
        this.cachedProducts = null;
        return Redirection.INDEX.getRedirect();
    }

    public String update() {
        productService.update(product);
        return Redirection.INDEX.getRedirect();
    }

    public String saveCategory() {
        categoryService.save(newCategory);
        this.newCategory = new Category();
        this.cachedCategories = null;
        return Redirection.INDEX.getRedirect();
    }

    public String updateCategory() {
        categoryService.update(category);
        return Redirection.INDEX.getRedirect();
    }

    public String editCategory(Category category) {
        this.category = category;
        return Redirection.EDIT_CATEGORY.getRedirect();
    }

    public String delete(Long categoryId) {
        categoryService.delete(categoryId);
        this.cachedCategories = null;
        return Redirection.PRODUCTS.getRedirect();
    }

    // Getter & Setter
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getNewCategory() {
        return newCategory;
    }
    public void setNewCategory(Category newCategory) {
        this.newCategory = newCategory;
    }
}
