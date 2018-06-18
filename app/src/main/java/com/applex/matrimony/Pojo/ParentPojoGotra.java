package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoGotra extends CommonParentPojo {

    @SerializedName("Gothra_Details")
    LinkedHashMap<String, ChildPojoGotra> objGotra;


    public LinkedHashMap<String, ChildPojoGotra> getObjGotra() {
        return objGotra;
    }

    public void setObjGotra(LinkedHashMap<String, ChildPojoGotra> objGotra) {
        this.objGotra = objGotra;
    }
}