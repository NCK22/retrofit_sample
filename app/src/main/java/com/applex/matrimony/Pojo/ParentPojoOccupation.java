package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoOccupation extends CommonParentPojo{

    @SerializedName("Occupation_Details")
    LinkedHashMap<String,ChildPojoOccupation> objOccupation;

    public LinkedHashMap<String, ChildPojoOccupation> getObjOccupation() {
        return objOccupation;
    }

    public void setObjOccupation(LinkedHashMap<String, ChildPojoOccupation> objOccupation) {
        this.objOccupation = objOccupation;
    }
}
