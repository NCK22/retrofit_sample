package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoEducation {

    @SerializedName("education_id")
    String education_id;

    @SerializedName("education_parent")
    String education_parent;

    @SerializedName("education")
    String education;

    @SerializedName("education_status")
    String education_status;

    public String getEducation() {
        return education;
    }

    public String getEducation_id() {
        return education_id;
    }

    public String getEducation_parent() {
        return education_parent;
    }

    public String getEducation_status() {
        return education_status;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setEducation_id(String education_id) {
        this.education_id = education_id;
    }

    public void setEducation_parent(String education_parent) {
        this.education_parent = education_parent;
    }

    public void setEducation_status(String education_status) {
        this.education_status = education_status;
    }
}

