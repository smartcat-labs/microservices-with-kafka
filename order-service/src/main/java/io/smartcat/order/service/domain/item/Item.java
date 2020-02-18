package io.smartcat.order.service.domain.item;

public class Item {

    private String id;
    private String category;
    private String name;
    private Double price;

    public Item(final String id, final String category, final String name, final Double price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }
}
