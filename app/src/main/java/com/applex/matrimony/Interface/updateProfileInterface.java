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
                                       @Field("complexion") String complexion,@Field("dob") String dob,@Field("marital_status") String marital_status,
                                       @Field("height") String height,@Field("weight") String weight, @Field("mother_language") String mother_language,
                                       @Field("eating") String eating,@Field("drinking") String drinking, @Field("smoking") String smoking);

    @FormUrlEncoded
    @POST("app/app_update_profile.php")
    Call<CommonParentPojo> updateReligion(@Field("user_id") String user_id,@Field("religion") String religion,@Field("caste")String caste,
                                       @Field("sub_caste") String sub_caste,@Field("raasi_id") String raasi,@Field("star_id") String star,
                                       @Field("gothra_id") String gothra_id,@Field("have_dosham") String have_dosham,@Field("birth_time") String birth_time,
                                       @Field("birth_country") String birth_country,@Field("birth_state") String birth_state, @Field("birth_city") String birth_city,
                                       @Field("chart_id") String chart_id);

}
