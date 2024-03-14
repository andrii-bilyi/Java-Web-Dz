package step.learning.dal.dto;

import java.text.DecimalFormat;
import java.util.UUID;

public class ActionItem {
    private UUID id;
    private String name;
    private String brand;
    private String type;
    private Integer oldPrice;
    private Integer newPrice;

    public ActionItem() {
    }

    public ActionItem(UUID id, String name, String brand, String type, Integer oldPrice, Integer newPrice) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Integer newPrice) {
        this.newPrice = newPrice;
    }
}
