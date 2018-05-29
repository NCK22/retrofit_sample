package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoMTongue extends CommonParentPojo{

    @SerializedName("Mothertongues_Details")
    LinkedHashMap<String,ChildPojoMTongue> objMTongue;


    public LinkedHashMap<String, ChildPojoMTongue> getObjMTongue() {
        return objMTongue;
    }

    public void setObjMTongue(LinkedHashMap<String, ChildPojoMTongue> objMTongue) {
        this.objMTongue = objMTongue;
    }
}
