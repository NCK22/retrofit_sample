package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ParentPojoState {

    @SerializedName("RESPONSE_STATUS")
    public
    String status;

    @SerializedName("RESPONSE_MSG")
    String msg;

    @SerializedName("State_Details")
    LinkedHashMap<String,ChildPojoState> objState;


    public String getMsg() {
        return msg;
    }

    public LinkedHashMap<String, ChildPojoState> getObjState() {
        return objState;
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

    public void setObjState(LinkedHashMap<String, ChildPojoState> objState) {
        this.objState = objState;
    }
}
