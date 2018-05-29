package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoReligion {

    @SerializedName("religion_id")
    String religion_id;

    @SerializedName("religion_name")
    String religion_name;

    @SerializedName("religion_status")
    String religion_status;

    public String getReligion_id() {
        return religion_id;
    }

    public String getReligion_name() {
        return religion_name;
    }

    public String getReligion_status() {
        return religion_status;
    }

    public void setReligion_id(String religion_id) {
        this.religion_id = religion_id;
    }

    public void setReligion_name(String religion_name) {
        this.religion_name = religion_name;
    }

    public void setReligion_status(String religion_status) {
        this.religion_status = religion_status;
    }
}
