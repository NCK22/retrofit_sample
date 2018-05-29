package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class CommonParentPojo {

    @SerializedName("RESPONSE_STATUS")
    public
    String status;

    @SerializedName("RESPONSE_MSG")
    String msg;

    public String getMsg() {
        return msg;
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


}
