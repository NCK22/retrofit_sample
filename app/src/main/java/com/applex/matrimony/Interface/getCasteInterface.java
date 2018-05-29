package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoCaste;
import com.applex.matrimony.Pojo.ParentPojoState;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface getCasteInterface {

    @FormUrlEncoded
    @POST("app/app_get_castes.php")
    Call<ParentPojoCaste> doGetListResources(@Field("religion") String religion);

}
