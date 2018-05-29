package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoCaste extends CommonParentPojo{

    @SerializedName("Caste_Details")
    LinkedHashMap<String,ChildPojoCaste> objCaste;


    public LinkedHashMap<String, ChildPojoCaste> getObjCaste() {
        return objCaste;
    }

    public void setObjCaste(LinkedHashMap<String, ChildPojoCaste> objCaste) {
        this.objCaste = objCaste;
    }
}
