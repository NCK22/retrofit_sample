package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface updateAboutInterface {

    @FormUrlEncoded
    @POST("app/app_update_about.php")
    Call<CommonParentPojo> updateAboutYou(@Field("user_id") String user_id, @Field("about_you") String about_you);


}
