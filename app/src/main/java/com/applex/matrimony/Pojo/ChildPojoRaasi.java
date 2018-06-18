package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoRaasi {

    @SerializedName("raasi_id")
    String raasi_id;

    @SerializedName("raasi_name")
    String raasi_name;

    @SerializedName("raasi_status")
    String raasi_status;

    public String getRaasi_id() {
        return raasi_id;
    }

    public String getRaasi_name() {
        return raasi_name;
    }

    public String getRaasi_status() {
        return raasi_status;
    }

    public void setRaasi_id(String raasi_id) {
        this.raasi_id = raasi_id;
    }

    public void setRaasi_name(String raasi_name) {
        this.raasi_name = raasi_name;
    }

    public void setRaasi_status(String raasi_status) {
        this.raasi_status = raasi_status;
    }
}
