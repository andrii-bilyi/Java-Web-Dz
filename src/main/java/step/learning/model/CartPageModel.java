package step.learning.model;

import step.learning.dal.dto.Cart;
import step.learning.dal.dto.CartItem;
import step.learning.dal.dto.Product;

import java.util.List;

public class CartPageModel {
    private List<Product> products;
    private List<CartItem> cartItems;
    private List<Cart> carts;

    public CartPageModel(List<Product> products, List<CartItem> cartItems, List<Cart> carts) {
        this.products = products;
        this.cartItems = cartItems;
        this.carts = carts;
    }

    public CartPageModel(List<Product> products, List<Cart> carts) {
        this.products = products;
        this.carts = carts;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public List<Cart> getCarts() {
        return carts;
    }
}
