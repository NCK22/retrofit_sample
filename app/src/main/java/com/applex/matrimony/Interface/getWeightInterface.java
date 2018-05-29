package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoHeight;
import com.applex.matrimony.Pojo.ParentPojoWeight;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getWeightInterface {

    @GET("app/app_get_weights.php")
    Call<ParentPojoWeight> doGetListResources();


}
