package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoMTongue {

    @SerializedName("mother_tongue_id")
    String mother_tongue_id;

    @SerializedName("mother_tongue_name")
    String mother_tongue_name;

    @SerializedName("mother_tongue_status")
    String mother_tongue_status;

    public String getMother_tongue_id() {
        return mother_tongue_id;
    }

    public String getMother_tongue_name() {
        return mother_tongue_name;
    }

    public String getMother_tongue_status() {
        return mother_tongue_status;
    }

    public void setMother_tongue_id(String mother_tongue_id) {
        this.mother_tongue_id = mother_tongue_id;
    }

    public void setMother_tongue_name(String mother_tongue_name) {
        this.mother_tongue_name = mother_tongue_name;
    }

    public void setMother_tongue_status(String mother_tongue_status) {
        this.mother_tongue_status = mother_tongue_status;
    }
}
