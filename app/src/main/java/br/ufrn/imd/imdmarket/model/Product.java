package br.ufrn.imd.imdmarket.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.Comparator;

import br.ufrn.imd.imdmarket.R;
import br.ufrn.imd.imdmarket.utils.ImageUtils;

public class Product {
    private String code;
    private String name;
    private String description;
    private int stock;
    private boolean isFavorite;
    private Drawable image;

    public Product(String code, String name, String description, int stock, boolean isFavorite) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.isFavorite = isFavorite;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

//    @Override
//    public String toString() {
//        return "Product{" +
//                "code='" + code + '\'' +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", stock=" + stock +
//                '}';
//    }


    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", isFavorite=" + isFavorite +
                '}';
    }

    public static Comparator<Product> comparator = (p1, p2) -> {
        if (p1.isFavorite() && !p2.isFavorite()) {
            return -1;
        } else if (!p1.isFavorite() && p2.isFavorite()) {
            return 1;
        } else {
        }
        return 0;
    };

    public Drawable getImage(Context context) {
        this.image = ImageUtils.getDrawableFromInternalStorage(context, code.concat(".png"));
        if(this.image != null) {
            return this.image;
        }

        return context.getResources().getDrawable(R.drawable.no_product);
    }
}
