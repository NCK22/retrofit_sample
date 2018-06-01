package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ParentPojoTabWhoMe extends CommonParentPojo {

    @SerializedName("Profile_Details")
    ArrayList<PojoProfile> objProfile;

    public ArrayList<PojoProfile> getObjProfile() {
        return objProfile;
    }

    public void setObjProfile(ArrayList<PojoProfile> objProfile) {
        this.objProfile = objProfile;
    }
}
