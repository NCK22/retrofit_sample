package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoHeight {

    @SerializedName("height_id")
    String height_id;

    @SerializedName("height")
    String height;

    public String getHeight() {
        return height;
    }

    public String getHeight_id() {
        return height_id;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setHeight_id(String height_id) {
        this.height_id = height_id;
    }
}

