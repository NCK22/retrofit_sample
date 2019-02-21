package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface getViewedMeInterface {


    @GET("app/app_get_viewed_me.php")
    Call<ParentPojoProfile> doGetListResources(@Query("matrimony_id") String matrimony_id);

}
