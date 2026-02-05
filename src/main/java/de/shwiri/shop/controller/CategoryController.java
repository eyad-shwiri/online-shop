package de.shwiri.shop.controller;

import de.shwiri.shop.model.Category;
import de.shwiri.shop.service.CategoryService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryService categoryService;

    private List<Category> allCategories;
    private Category selectedCategory;

    @PostConstruct
    public void init() {
        allCategories = categoryService.findAll();
    }

    public List<Category> getAllCategories() { return allCategories; }
    public Category getSelectedCategory() { return selectedCategory; }
}
