package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoStar {

    @SerializedName("star_id")
    String star_id;

    @SerializedName("star_name")
    String star_name;

    @SerializedName("star_status")
    String star_status;


    public String getStar_id() {
        return star_id;
    }

    public String getStar_name() {
        return star_name;
    }

    public String getStar_status() {
        return star_status;
    }

    public void setStar_id(String star_id) {
        this.star_id = star_id;
    }

    public void setStar_name(String star_name) {
        this.star_name = star_name;
    }

    public void setStar_status(String star_status) {
        this.star_status = star_status;
    }
}
