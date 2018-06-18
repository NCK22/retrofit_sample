package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoGotra {

    @SerializedName("gothra_id")
    String gothra_id;

    @SerializedName("gothra_name")
    String gothra_name;

    @SerializedName("gothra_status")
    String gothra_status;

    public String getGothra_id() {
        return gothra_id;
    }

    public String getGothra_name() {
        return gothra_name;
    }

    public String getGothra_status() {
        return gothra_status;
    }

    public void setGothra_id(String gothra_id) {
        this.gothra_id = gothra_id;
    }

    public void setGothra_status(String gothra_status) {
        this.gothra_status = gothra_status;
    }

    public void setGothra_name(String gothra_name) {
        this.gothra_name = gothra_name;
    }
}
