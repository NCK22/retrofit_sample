package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ParentPojoProfile extends CommonParentPojo {

    @SerializedName("Profile_Details")
    ArrayList<ChildPojoProfile> objProfile;

    public ArrayList<ChildPojoProfile> getObjProfile() {
        return objProfile;
    }

    public void setObjProfile(ArrayList<ChildPojoProfile> objProfile) {
        this.objProfile = objProfile;
    }
}
