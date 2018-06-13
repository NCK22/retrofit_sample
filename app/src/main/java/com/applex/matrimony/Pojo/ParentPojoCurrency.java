package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoCurrency {

    @SerializedName("RESPONSE_STATUS")
    public
    String status;

    @SerializedName("RESPONSE_MSG")
    String msg;

    @SerializedName("Currency_Details")
    LinkedHashMap<String,ChildPojoCurrency> objCurrency;

    public LinkedHashMap<String, ChildPojoCurrency> getObjCurrency() {
        return objCurrency;
    }

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

    public void setObjCurrency(LinkedHashMap<String, ChildPojoCurrency> objCurrency) {
        this.objCurrency = objCurrency;
    }
}
