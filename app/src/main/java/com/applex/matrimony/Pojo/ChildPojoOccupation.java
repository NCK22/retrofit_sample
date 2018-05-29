package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoOccupation {

    @SerializedName("occupation_id")
    String occupation_id;

    @SerializedName("occupation_parent")
    String occupation_parent;

    @SerializedName("occupation")
    String occupation;

    @SerializedName("occupation_status")
    String occupation_status;

    public String getOccupation() {
        return occupation;
    }

    public String getOccupation_id() {
        return occupation_id;
    }

    public String getOccupation_parent() {
        return occupation_parent;
    }

    public String getOccupation_status() {
        return occupation_status;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setOccupation_id(String occupation_id) {
        this.occupation_id = occupation_id;
    }

    public void setOccupation_parent(String occupation_parent) {
        this.occupation_parent = occupation_parent;
    }

    public void setOccupation_status(String occupation_status) {
        this.occupation_status = occupation_status;
    }
}

