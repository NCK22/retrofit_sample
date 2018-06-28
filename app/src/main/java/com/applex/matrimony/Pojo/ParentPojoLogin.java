package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoLogin {

    @SerializedName("RESPONSE_STATUS")
    public
    String status;

    @SerializedName("RESPONSE_MSG")
    String msg;

    @SerializedName("Profile_Details")
    LinkedHashMap<String,String> objProfile;


    public String getMsg() {
        return msg;
    }

    public LinkedHashMap<String, String> getObjProfile() {
        return objProfile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setObjProfile(LinkedHashMap<String, String> objProfile) {
        this.objProfile = objProfile;
    }
}
