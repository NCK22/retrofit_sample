package com.applex.matrimony.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ParentPojoStar extends CommonParentPojo {

    @SerializedName("Stars_Details")
    LinkedHashMap<String, ChildPojoStar> objStar;

    public LinkedHashMap<String, ChildPojoStar> getObjStar() {
        return objStar;
    }

    public void setObjStar(LinkedHashMap<String, ChildPojoStar> objStar) {
        this.objStar = objStar;
    }
}