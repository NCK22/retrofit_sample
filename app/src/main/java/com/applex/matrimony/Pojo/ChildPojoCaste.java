package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoCaste {

    @SerializedName("caste_id")
    String caste_id;

    @SerializedName("caste_name")
    String caste_name;

    @SerializedName("religion_id")
    String religion_id;

    @SerializedName("caste_status")
    String caste_status;


    public String getReligion_id() {
        return religion_id;
    }

    public String getCaste_id() {
        return caste_id;
    }

    public String getCaste_name() {
        return caste_name;
    }

    public String getCaste_status() {
        return caste_status;
    }

    public void setReligion_id(String religion_id) {
        this.religion_id = religion_id;
    }

    public void setCaste_id(String caste_id) {
        this.caste_id = caste_id;
    }

    public void setCaste_name(String caste_name) {
        this.caste_name = caste_name;
    }

    public void setCaste_status(String caste_status) {
        this.caste_status = caste_status;
    }
}
