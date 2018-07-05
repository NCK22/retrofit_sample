package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentPojoLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface editEmailInterface {

    @FormUrlEncoded
    @POST("app/app_update_email.php")
    Call<CommonParentPojo> doGetListResources(@Field("email") String email,@Field("user_id") String user_id);


}
