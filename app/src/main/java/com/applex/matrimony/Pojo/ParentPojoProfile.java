package com.applex.matrimony.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ParentPojoProfile extends CommonParentPojo implements Parcelable {

    @SerializedName("Profile_Details")
    ArrayList<ChildPojoProfile> objProfile;

    protected ParentPojoProfile(Parcel in) {
    }

    public static final Creator<ParentPojoProfile> CREATOR = new Creator<ParentPojoProfile>() {
        @Override
        public ParentPojoProfile createFromParcel(Parcel in) {
            return new ParentPojoProfile(in);
        }

        @Override
        public ParentPojoProfile[] newArray(int size) {
            return new ParentPojoProfile[size];
        }
    };

    public ArrayList<ChildPojoProfile> getObjProfile() {
        return objProfile;
    }

    public void setObjProfile(ArrayList<ChildPojoProfile> objProfile) {
        this.objProfile = objProfile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
