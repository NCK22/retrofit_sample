package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentPojoLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface loginInterface {

    @FormUrlEncoded
    @POST("app/app_login.php")
    Call<ParentPojoLogin> doGetListResources(@Field("username") String username, @Field("password") String password);


}
