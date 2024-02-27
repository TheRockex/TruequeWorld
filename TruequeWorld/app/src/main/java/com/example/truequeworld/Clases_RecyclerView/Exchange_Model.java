package com.example.truequeworld.Clases_RecyclerView;

import android.graphics.drawable.Drawable;

public class Exchange_Model {
    public String myImg;
    public String otherImg;
    public Drawable up_logo;
    public Drawable down_logo;

    public Exchange_Model(String myImg, String otherImg, Drawable up_logo, Drawable down_logo) {
        this.myImg = myImg;
        this.otherImg = otherImg;
        this.up_logo = up_logo;
        this.down_logo = down_logo;
    }
    public String getMyImg() {
        return myImg;
    }

    public String getOtherImg() {
        return otherImg;
    }
}
