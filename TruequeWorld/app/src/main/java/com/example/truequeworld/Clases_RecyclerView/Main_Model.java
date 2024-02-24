package com.example.truequeworld.Clases_RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Main_Model {
    public String mainName;
    public String mainEx;
    public Bitmap mainImg;
    public Drawable mainSave;
    private Integer productId;

    public Main_Model(String mainName, String mainEx, Bitmap mainImg, Drawable mainSave, Integer productId) {
        this.mainName = mainName;
        this.mainEx = mainEx;
        this.mainImg = mainImg;
        this.mainSave = mainSave;
        this.productId = productId;
    }

    public String getMainName() {
        return mainName;
    }

    public String getMainEx() {
        return mainEx;
    }

    public Bitmap getMainImg() {
        return mainImg;
    }

    public Drawable getMainSave() {
        return mainSave;
    }

    public Integer getId() {
        return productId;
    }
}
