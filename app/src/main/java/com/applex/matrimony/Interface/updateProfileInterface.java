package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentPojoProfile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface updateProfileInterface {

    @FormUrlEncoded
    @POST("app/app_update_profile.php")
    Call<CommonParentPojo> updateBasic(@Field("user_id") String user_id,@Field("profile_for") String profileFor,@Field("profile_name")String profile_name,
                                       @Field("age") String age,@Field("body_type") String body_type,@Field("physical_status") String physical_status,
                                       @Field("complexion") String complexion,@Field("dob") String dob,@Field("maritial_status") String marital_status,
                                       @Field("height_id") String height,@Field("weight_id") String weight, @Field("mother_language") String mother_language,
                                       @Field("eating") String eating,@Field("drinking") String drinking, @Field("smoking") String smoking);

    @FormUrlEncoded
    @POST("app/app_update_profile.php")
    Call<CommonParentPojo> updateReligion(@Field("user_id") String user_id,@Field("religion") String religion,@Field("caste")String caste,
                                       @Field("sub_caste") String sub_caste,@Field("raasi_id") String raasi,@Field("star_id") String star,
                                       @Field("gothra_id") String gothra_id,@Field("have_dosham") String have_dosham,@Field("birth_time") String birth_time,
                                       @Field("birth_country") String birth_country,@Field("birth_state") String birth_state, @Field("birth_city") String birth_city,
                                       @Field("chart_id") String chart_id);



    @FormUrlEncoded
    @POST("app/app_update_profile.php")
    Call<CommonParentPojo> updateGroomBrideLoc(@Field("user_id") String user_id,@Field("country") String country,@Field("state")String state,
                                          @Field("city") String city,@Field("resident_status") String resident_status,@Field("parish") String parish,
                                          @Field("parish_village") String parish_village);



    @FormUrlEncoded
    @POST("app/app_update_profile.php")
    Call<CommonParentPojo> updateProfessional(@Field("user_id") String user_id,@Field("education_id") String education,@Field("college")String college,
                                               @Field("education_detail") String education_detail,@Field("occupation_id") String occupation,
                                              @Field("occupation_detail") String occupation_detail,@Field("employed_in") String employed_in,@Field("income") String income);

    @FormUrlEncoded
    @POST("app/app_update_profile.php")
    Call<CommonParentPojo> updateFamilyDetails(@Field("user_id") String user_id,@Field("family_value") String family_value,@Field("family_type")String family_type,
                                              @Field("family_status") String family_status,@Field("family_origin") String family_origin,
                                              @Field("family_location") String family_location,@Field("father_status") String father_status,@Field("mother_status") String mother_status,
                                               @Field("brothers") String brothers,@Field("sisters") String sisters);

    @FormUrlEncoded
    @POST("app/app_update_profile.php")
    Call<CommonParentPojo> updateAboutYou(@Field("user_id") String user_id,@Field("about_you") String about_you);


    @FormUrlEncoded
    @POST("app/app_update_profile.php")
    Call<CommonParentPojo> updateAboutFam(@Field("user_id") String user_id,@Field("family_about") String family_about);

}
