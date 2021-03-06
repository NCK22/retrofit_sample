package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoProfile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface getProfileInterface {

    @FormUrlEncoded
    @POST("app/app_get_profile.php")
    Call<ParentPojoProfile> doGetListResources(@Field("matrimony_id") String matrimony_id);


}
