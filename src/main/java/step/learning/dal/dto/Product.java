package step.learning.dal.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private double price;
    private String description;
    private String image;

    public Product() {
    }

    public Product(ResultSet resultSet) throws SQLException {
        this(
                UUID.fromString(resultSet.getString("product_id")),
                resultSet.getString("product_name"),
                resultSet.getDouble("product_price"),
                resultSet.getString("product_description"),
                resultSet.getString("product_image")
        );
    }

    public Product(UUID id, String name, double price, String description,  String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
