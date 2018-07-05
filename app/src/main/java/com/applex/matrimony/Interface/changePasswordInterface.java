package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface changePasswordInterface {

    @FormUrlEncoded
    @POST("app/app_update_password.php")
    Call<CommonParentPojo> doGetListResources(@Field("old_password") String old_password,
                                              @Field("new_password") String new_password,
                                              @Field("conf_password") String conf_password,
                                              @Field("user_id") String user_id);


}
