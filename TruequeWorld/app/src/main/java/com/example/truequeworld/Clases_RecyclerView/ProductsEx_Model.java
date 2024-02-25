package com.example.truequeworld.Clases_RecyclerView;

import android.graphics.Bitmap;

public class ProductsEx_Model {
    public String productName;
    public Bitmap productImg;
    private Integer productId;

    public ProductsEx_Model(String productName, Bitmap productImg, Integer productId) {
        this.productName = productName;
        this.productImg = productImg;
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public Bitmap getProductImg() {
        return productImg;
    }

    public Integer getId() {
        return productId;
    }

}
