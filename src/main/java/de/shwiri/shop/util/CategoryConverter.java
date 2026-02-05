package de.shwiri.shop.util;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import de.shwiri.shop.model.Category;
import jakarta.inject.Inject;
import de.shwiri.shop.service.CategoryService; // Angenommen, du hast diesen Service

@FacesConverter(value = "categoryConverter", managed = true)
public class CategoryConverter implements Converter<Category> {

    @Inject
    private CategoryService repository;

    @Override
    public Category getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) return null;
        return repository.findById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Category value) {
        if (value == null) return "";
        return String.valueOf(value.getId());
    }
}
