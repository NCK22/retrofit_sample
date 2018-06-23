package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ParentPojoPartnerPref extends CommonParentPojo {

    @SerializedName("Preference_Details")
    ArrayList<ChildPojoPartnerPref> objProfile;

    public ArrayList<ChildPojoPartnerPref> getObjProfile() {
        return objProfile;
    }

    public void setObjProfile(ArrayList<ChildPojoPartnerPref> objProfile) {
        this.objProfile = objProfile;
    }
}
