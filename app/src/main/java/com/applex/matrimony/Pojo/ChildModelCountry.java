package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChildModelCountry {

    @SerializedName("country_id")
    String country_id;

    @SerializedName("country_name")
    String country_name;

    @SerializedName("country_currency")
    String country_currency;

    @SerializedName("country_extension")
    String country_extension;

    @SerializedName("country_status")
    String country_status;


    public String getCountry_id() {
        return country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getCountry_currency() {
        return country_currency;
    }

    public String getCountry_extension() {
        return country_extension;
    }

    public String getCountry_status() {
        return country_status;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public void setCountry_currency(String country_currency) {
        this.country_currency = country_currency;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public void setCountry_extension(String country_extension) {
        this.country_extension = country_extension;
    }

    public void setCountry_status(String country_status) {
        this.country_status = country_status;
    }
}
