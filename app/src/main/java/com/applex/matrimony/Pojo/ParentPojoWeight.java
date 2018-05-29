package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoWeight extends CommonParentPojo{

    @SerializedName("Weight_Details")
    LinkedHashMap<String,ChildPojoWeight> objWeight;

    public LinkedHashMap<String, ChildPojoWeight> getObjWeight() {
        return objWeight;
    }

    public void setObjWeight(LinkedHashMap<String, ChildPojoWeight> objWeight) {
        this.objWeight = objWeight;
    }
}
