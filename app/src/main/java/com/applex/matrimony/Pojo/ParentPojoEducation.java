package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoEducation extends CommonParentPojo {

    @SerializedName("Education_Details")
    LinkedHashMap<String, ChildPojoEducation> objEducation;

    public LinkedHashMap<String, ChildPojoEducation> getObjEducation() {
        return objEducation;
    }

    public void setObjEducation(LinkedHashMap<String, ChildPojoEducation> objEducation) {
        this.objEducation = objEducation;
    }
}
