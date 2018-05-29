package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoState {

    @SerializedName("state_id")
    String state_id;

    @SerializedName("state_name")
    String state_name;

    @SerializedName("country_id")
    String country_id;

    @SerializedName("state_status")
    String state_status;

    public String getState_id() {
        return state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public String getState_status() {
        return state_status;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public void setState_status(String state_status) {
        this.state_status = state_status;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }
}
