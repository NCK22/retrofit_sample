package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoReligion {

    @SerializedName("RESPONSE_STATUS")
    public
    String status;

    @SerializedName("RESPONSE-MSG")
    String msg;

    @SerializedName("Religion_Details")
    LinkedHashMap<String,ChildPojoReligion> objReligion;


    public String getMsg() {
        return msg;
    }

    public LinkedHashMap<String, ChildPojoReligion> getObjReligion() {
        return objReligion;
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

    public void setObjReligion(LinkedHashMap<String, ChildPojoReligion> objReligion) {
        this.objReligion = objReligion;
    }
}
