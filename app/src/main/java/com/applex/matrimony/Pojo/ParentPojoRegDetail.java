package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ParentPojoRegDetail extends CommonParentPojo {

    @SerializedName("Profile_details")
    LinkedHashMap<String,String> objDetail;

    public LinkedHashMap<String, String> getObjDetail() {
        return objDetail;
    }

    public void setObjDetail(LinkedHashMap<String, String> objDetail) {
        this.objDetail = objDetail;
    }
}
