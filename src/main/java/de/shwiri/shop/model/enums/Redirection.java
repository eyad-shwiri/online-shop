package de.shwiri.shop.model.enums;

public enum Redirection {
    LOGIN("login?faces-redirect=true"),
    PRODUCTS("products?faces-redirect=true"),
    EDIT_PRODUCT("edit-product?faces-redirect=true"),
    EDIT_CATEGORY("edit-category?faces-redirect=true"),
    ORDER_SUCCESS("order-success?faces-redirect=true"),
    INDEX("index?faces-redirect=true"),
    ERROR("error?faces-redirect=true");

    private final String redirect;

    Redirection(String redirect) {
        this.redirect = redirect;
    }

    public String getRedirect() {
        return redirect;
    }
}
