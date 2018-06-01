package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoTabWhoMe;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface getViewedMeInterface {

    @FormUrlEncoded
    @GET("app/app_get_viewed_me.php")
    Call<ParentPojoTabWhoMe> doGetListResources(@Field("matrimony_id") String matrimony_id);


}
