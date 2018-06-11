package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoTabWhoMe;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface getInterestedMyInterface {

    @FormUrlEncoded
    @POST("app/app_get_interested_my.php")
    Call<ParentPojoTabWhoMe> doGetListResources(@Field("matrimony_id") String matrimony_id);


}
