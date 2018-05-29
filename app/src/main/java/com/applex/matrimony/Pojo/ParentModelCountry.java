package com.applex.matrimony.Pojo;

import com.applex.matrimony.Pojo.ChildModelCountry;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ParentModelCountry {

    @SerializedName("RESPONSE_STATUS")
    public
    String status;

    @SerializedName("RESPONSE_MSG")
    String msg;

    @SerializedName("Country_Details")
    LinkedHashMap<String,ChildModelCountry> objCountry;


    public String getMsg() {
        return msg;
    }

    public LinkedHashMap<String, ChildModelCountry> getObjCountry() {
        return objCountry;
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

    public void setObjCountry(LinkedHashMap<String, ChildModelCountry> objCountry) {
        this.objCountry = objCountry;
    }
}
