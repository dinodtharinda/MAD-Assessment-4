package com.example.mad_assessment_4.data.models;

public class Pizza {
    private int id;
    private String name;
    private String description;
    private String imagePath;
    private String size;
    private boolean topping;
    private double price;

    // Constructor
    public Pizza(int id, String name, String description, String imagePath, String size, boolean topping, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        setSize(size);
        this.topping = topping;
        setPrice(price);
    }

    // Getter and Setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (size.equalsIgnoreCase("small") || size.equalsIgnoreCase("medium") || size.equalsIgnoreCase("large")) {
            this.size = size;
        } else {
            throw new IllegalArgumentException("Invalid size. Valid sizes are: small, medium, large.");
        }
    }

    public boolean isTopping() {
        return topping;
    }

    public void setTopping(boolean topping) {
        this.topping = topping;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", size='" + size + '\'' +
                ", topping=" + topping +
                ", price=" + price +
                '}';
    }
}
