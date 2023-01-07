package pl.shop.shopapp.utils;

public enum Error {
    PRODUCT_NOT_FOUND(404, "Product not found in database."),
    CART_NOT_FOUND(404, "Cart not found in database."),
    CLIENT_NOT_FOUND(404, "Client not found in database.");

    private final int code;
    private final String description;

    Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
