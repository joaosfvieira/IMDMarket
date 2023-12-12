package br.ufrn.imd.imdmarket.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import br.ufrn.imd.imdmarket.R;
import br.ufrn.imd.imdmarket.utils.ImageUtils;

public class Product {
    private String code;
    private String name;
    private String description;
    private int stock;
    private Drawable image;

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

    public void setImage(Drawable image) {
        this.image = image;
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

    public Drawable getImage(Context context) {
        this.image = ImageUtils.getDrawableFromInternalStorage(context, code.concat(".png"));
        if(this.image != null) {
            return this.image;
        }

        return context.getResources().getDrawable(R.drawable.no_product);
    }
}
