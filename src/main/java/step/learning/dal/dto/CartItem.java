package step.learning.dal.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CartItem {
    private UUID id;
    private UUID cartId;
    private UUID productId;
    private int count;

    public CartItem() {
    }

    public CartItem(ResultSet resultSet) throws SQLException {
        this.id = UUID.fromString(resultSet.getString("cart_dt_id"));
        this.cartId = UUID.fromString(resultSet.getString("cart_id"));
        this.productId = UUID.fromString(resultSet.getString("product_id"));
        this.count = resultSet.getInt("cart_dt_cnt");
//        this(
//                UUID.fromString(resultSet.getString("cart_id")),
//                UUID.fromString(resultSet.getString("product_id")),
//                resultSet.getInt("cart_dt_cnt")
//        );
    }

    public CartItem(UUID id, UUID cartId, UUID productId, int count) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.count = count;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
