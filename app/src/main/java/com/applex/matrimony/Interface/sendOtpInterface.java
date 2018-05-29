package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentPojoCustReg;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface sendOtpInterface {

    @FormUrlEncoded
    @POST("app/app_send_otp.php")
    Call<CommonParentPojo> doGetListResources(
            @Field("email_id") String email_id,
            @Field("matrimony_id") String matrimony_id,
            @Field("phone") String phone
    );


}
