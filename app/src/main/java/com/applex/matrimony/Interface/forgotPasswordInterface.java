package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface forgotPasswordInterface {

    @FormUrlEncoded
    @POST("app/app_forgot_password.php")
    Call<CommonParentPojo> doGetListResources(  @Field("email_id") String username );


}
