package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

public class ChildPojoCustReg {

    @SerializedName("profile_id")
    String profile_id;

    @SerializedName("matrimony_id")
    String matrimony_id;

    @SerializedName("gender")
    String gender;

    @SerializedName("religion")
    String religion;

    @SerializedName("dob")
    String dob;

    @SerializedName("email")
    String email;

    @SerializedName("mobile")
    String mobile;

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public String getMatrimony_id() {
        return matrimony_id;
    }

    public String getReligion() {
        return religion;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setMatrimony_id(String matrimony_id) {
        this.matrimony_id = matrimony_id;
    }
}

