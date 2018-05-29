package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoHeight extends CommonParentPojo{

    @SerializedName("Height_Details")
    LinkedHashMap<String,ChildPojoHeight> objHeight;

    public LinkedHashMap<String, ChildPojoHeight> getObjHeight() {
        return objHeight;
    }

    public void setObjHeight(LinkedHashMap<String, ChildPojoHeight> objHeight) {
        this.objHeight = objHeight;
    }
}
