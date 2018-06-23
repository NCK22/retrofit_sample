package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoPartnerPref;
import com.applex.matrimony.Pojo.ParentPojoProfile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface getPreferenceInterface {

    @FormUrlEncoded
    @POST("app/app_get_preference.php")
    Call<ParentPojoPartnerPref> doGetListResources(@Field("matrimony_id") String matrimony_id);


}
