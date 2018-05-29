package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoCity {

    @SerializedName("RESPONSE_STATUS")
    public
    String status;

    @SerializedName("RESPONSE_MSG")
    String msg;

    @SerializedName("City_Details")
    LinkedHashMap<String,ChildPojoCity> objCity;


    public String getMsg() {
        return msg;
    }

    public LinkedHashMap<String, ChildPojoCity> getObjCity() {
        return objCity;
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

    public void setObjCity(LinkedHashMap<String, ChildPojoCity> objCity) {
        this.objCity = objCity;
    }
}
