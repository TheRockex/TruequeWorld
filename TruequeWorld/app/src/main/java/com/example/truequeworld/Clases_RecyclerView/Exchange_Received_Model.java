package com.example.truequeworld.Clases_RecyclerView;

import android.graphics.drawable.Drawable;
import android.view.View;

public class Exchange_Received_Model {

    public String myImg;
    public String otherImg;
    public Drawable up_logo;
    public Drawable down_logo;
    public boolean isSelected;

    public View.OnClickListener acceptEx;
    public View.OnClickListener rejectEx;

    public Exchange_Received_Model(String myImg, String otherImg, Drawable up_logo, Drawable down_logo, View.OnClickListener acceptEx, View.OnClickListener rejectEx) {
        this.myImg = myImg;
        this.otherImg = otherImg;
        this.up_logo = up_logo;
        this.down_logo = down_logo;
        this.acceptEx = acceptEx;
        this.rejectEx = rejectEx;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getMyImg() {
        return myImg;
    }

    public String getOtherImg() {
        return otherImg;
    }

    public View.OnClickListener getAcceptEx() {
        return acceptEx;
    }

    public View.OnClickListener getRejectEx() {
        return rejectEx;
    }
}
