package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoProfile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface searchBasicInterface {

    @FormUrlEncoded
    @POST("app/app_basic_search.php")
    Call<ParentPojoProfile> searchBasic(@Field("matrimony_id") String matrimony_id,@Field("age_from") String age_from,@Field("age_to") String age_to,
                                               @Field("religion") String religion,@Field("caste") String caste,@Field("gender") String gender);


    @FormUrlEncoded
    @POST("app/app_search_id.php")
    Call<ParentPojoProfile> searchById(@Field("matrimony_id_from") String matrimony_id_from,@Field("matrimony_id") String matrimony_id)              ;


}
