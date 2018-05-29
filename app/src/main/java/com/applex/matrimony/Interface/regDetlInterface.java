package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentPojoCustReg;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface regDetlInterface {

    @FormUrlEncoded
    @POST("app/app_reg_details.php")
    Call<CommonParentPojo> doGetListResources(
            @Field("maritial_status") String maritial_status,
            @Field("children_number") String children_number,
            @Field("willing") String willing,
            @Field("sub_caste") String sub_caste,
            @Field("country") String country,
            @Field("state") String state,
            @Field("city") String city,
            @Field("city_name") String city_name,
            @Field("parish") String parish,
            @Field("resident_status") String resident_status,
            @Field("citizenship") String citizenship,
            @Field("income") String income,
            @Field("country_currency") String country_currency,
            @Field("feet") String feet,
            @Field("weight") String weight,
            @Field("body_type") String body_type,
            @Field("complexion") String complexion,
            @Field("physical_status") String physical_status,
            @Field("education") String education,
            @Field("occupation") String occupation,
            @Field("employed_in") String employed_in,
            @Field("food") String food,
             @Field("smoking") String smoking,
            @Field("drinking") String drinking,
            @Field("star") String star,
            @Field("raasi") String raasi,
            @Field("family_status") String family_status,
            @Field("family_type") String family_type,
            @Field("family_value") String family_value,
            @Field("about_you") String about_you,
            @Field("profile_id") String profile_id

    );


}
