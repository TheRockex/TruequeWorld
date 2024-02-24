package com.example.truequeworld.Clases_RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

public class Saved_Model {
    private String favProduct_name;
    private Integer favProduct_precio;
    private Integer favProduct_propietarioID;
    private Bitmap favProduct_img;
    public Drawable mainSave;

    private Integer productId;

    public Saved_Model(String favProduct_name, Integer favProduct_precio, Integer favProduct_propietarioID, Bitmap favProduct_img, Drawable mainSave, Integer productId) {
        this.favProduct_name = favProduct_name;
        this.favProduct_precio = favProduct_precio;
        this.favProduct_propietarioID = favProduct_propietarioID;
        this.favProduct_img = favProduct_img;
        this.mainSave = mainSave;
        this.productId = productId;
    }

    public String getFavProduct_name() {
        return favProduct_name;
    }

    public Integer getFavProduct_precio() {
        return favProduct_precio;
    }

    public Integer getFavProduct_propietarioID() {
        return favProduct_propietarioID;
    }

    public Bitmap getFavProduct_img() {
        return favProduct_img;
    }

    public Drawable getMainSave() {
        return mainSave;
    }

    public Integer getId() {
        return productId;
    }

}
