package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoCustReg extends CommonParentPojo {

    @SerializedName("Profile_Details")
    ChildPojoCustReg objProfile;


    public ChildPojoCustReg getObjProfile() {
        return objProfile;
    }

    public void setObjProfile(ChildPojoCustReg objProfile) {
        this.objProfile = objProfile;
    }
}
