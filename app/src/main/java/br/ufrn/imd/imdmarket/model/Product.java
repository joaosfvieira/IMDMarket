package br.ufrn.imd.imdmarket.model;

import br.ufrn.imd.imdmarket.R;

public class Product {
    private String code;
    private String name;
    private String description;
    private int stock;
    private int image;

    public Product(String code, String name, String description, int stock) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                '}';
    }

    public int getImage() {
        return this.image != 0 ? this.image : R.drawable.no_product;
    }
}
