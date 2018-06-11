package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoTabWhoMe;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface getShortlistedMyInterface {

    @FormUrlEncoded
    @POST("app/app_get_shortlisted_my.php")
    Call<ParentPojoTabWhoMe> doGetListResources(@Field("matrimony_id") String matrimony_id);


}
