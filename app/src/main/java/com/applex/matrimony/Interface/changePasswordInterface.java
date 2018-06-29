package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface changePasswordInterface {

    @FormUrlEncoded
    @POST("app/app_change_password.php")
    Call<CommonParentPojo> doGetListResources(@Field("current_password") String current_password,@Field("new_password") String new_password,@Field("confirm_new_password") String confirm_new_password);


}
