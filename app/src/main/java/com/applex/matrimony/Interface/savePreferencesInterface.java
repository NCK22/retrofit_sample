package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface savePreferencesInterface {

    @FormUrlEncoded
    @POST("app/app_save_preference.php")
    Call<CommonParentPojo> updateBasic(@Field("matrimony_id") String user_id, @Field("age_from") String age,@Field("age_to") String ageto, @Field("maritial_status") String marital_status,
                                       @Field("physical_status") String physical_status,@Field("height_from_id") String height,@Field("height_to_id") String height_to,
                                       @Field("eating_habit") String eating, @Field("drinking_habit") String drinking,@Field("smoking_habit") String smoking);

    @FormUrlEncoded
    @POST("app/app_save_preference.php")
    Call<CommonParentPojo> updateReligion(@Field("matrimony_id") String user_id, @Field("religion") String religion, @Field("mother_language") String mother_language, @Field("caste") String caste);


    @FormUrlEncoded
    @POST("app/app_save_preference.php")
    Call<CommonParentPojo> updateGroomBrideLoc(@Field("matrimony_id") String user_id, @Field("country") String country, @Field("state") String state);


    @FormUrlEncoded
    @POST("app/app_save_preference.php")
    Call<CommonParentPojo> updateProfessional(@Field("matrimony_id") String user_id, @Field("education") String education, @Field("occupation") String occupation,
                                              @Field("min_income") String min_income,@Field("max_income") String max_income);

    @FormUrlEncoded
    @POST("app/app_save_preference.php")
    Call<CommonParentPojo> updateAboutYou(@Field("matrimony_id") String user_id, @Field("about_partner") String about_you);


}