package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoRaasi extends CommonParentPojo {

    @SerializedName("Raasi_Details")
    LinkedHashMap<String, ChildPojoRaasi> objRaasi;


    public LinkedHashMap<String, ChildPojoRaasi> getObjRaasi() {
        return objRaasi;
    }

    public void setObjRaasi(LinkedHashMap<String, ChildPojoRaasi> objRaasi) {
        this.objRaasi = objRaasi;
    }
}