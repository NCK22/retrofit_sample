package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ParentPojoGallery extends CommonParentPojo {

    @SerializedName("Gallery_Details")
    LinkedHashMap<String, ArrayList<String>> objGallery;

    public LinkedHashMap<String, ArrayList<String>> getObjGallery() {
        return objGallery;
    }

    public void setObjGallery(LinkedHashMap<String, ArrayList<String>> objGallery) {
        this.objGallery = objGallery;
    }
}
