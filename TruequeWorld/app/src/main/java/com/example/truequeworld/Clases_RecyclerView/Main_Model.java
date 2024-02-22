package com.example.truequeworld.Clases_RecyclerView;

import android.graphics.drawable.Drawable;

public class Main_Model {
    public String mainName;
    public String mainEx;
    public String mainImg;
    public Drawable mainSave;
    public boolean isSelected;

    public Main_Model(String mainName, String mainEx, String mainImg, Drawable mainSave) {
        this.mainName = mainName;
        this.mainEx = mainEx;
        this.mainImg = mainImg;
        this.mainSave = mainSave;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getMainName() {
        return mainName;
    }

    //Tiene que ser siempre truequear
    public String getMainEx() {
        return mainEx;
    }

    public String getMainImg() {
        return mainImg;
    }

    public Drawable getMainSave() {
        return mainSave;
    }
}
