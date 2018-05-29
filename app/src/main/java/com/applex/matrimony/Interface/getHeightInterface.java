package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoHeight;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getHeightInterface {

    @GET("app/app_get_heights.php")
    Call<ParentPojoHeight> doGetListResources();


}
