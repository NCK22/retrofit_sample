package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoCity {

    @SerializedName("city_id")
    String city_id;

    @SerializedName("city_name")
    String city_name;

    @SerializedName("state_id")
    String state_id;

    @SerializedName("country_id")
    String country_id;

    @SerializedName("city_status")
    String city_status;


    public String getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public String getCity_status() {
        return city_status;
    }

    public String getState_id() {
        return state_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public void setCity_status(String city_status) {
        this.city_status = city_status;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }
}
