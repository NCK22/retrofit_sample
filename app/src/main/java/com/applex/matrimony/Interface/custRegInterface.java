package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoCustReg;
import com.applex.matrimony.Pojo.ParentPojoState;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface custRegInterface {

    @FormUrlEncoded
    @POST("app/app_customer_reg.php")
    Call<ParentPojoCustReg> doGetListResources(
            @Field("profile_for") String profile_for,
            @Field("name") String name,
            @Field("gender") String gender,
            @Field("dob") String dob,
            @Field("religion") String religion,
            @Field("caste") String caste,
            @Field("mother_tongue") String mother_tongue,
            @Field("phone_countrycode") String phone_countrycode,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("password") String password
            );


}
