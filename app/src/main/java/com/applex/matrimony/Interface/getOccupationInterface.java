package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoHeight;
import com.applex.matrimony.Pojo.ParentPojoOccupation;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getOccupationInterface {

    @GET("app/app_get_occupations.php")
    Call<ParentPojoOccupation> doGetListResources();


}
