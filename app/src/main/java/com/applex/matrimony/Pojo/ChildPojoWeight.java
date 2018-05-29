package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoWeight {

    @SerializedName("weight_id")
    String weight_id;

    @SerializedName("weight")
    String weight;

    public String getWeight() {
        return weight;
    }

    public String getWeight_id() {
        return weight_id;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setWeight_id(String weight_id) {
        this.weight_id = weight_id;
    }
}

