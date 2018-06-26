package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentPojoCustReg;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface checkOtpInterface {

    @FormUrlEncoded
    @POST("app/app_check_otp.php")
    Call<CommonParentPojo> doGetListResources(
            @Field("matrimony_id") String matrimony_id,
            @Field("otp") String otp,
            @Field("phone")String phone
    );


}
