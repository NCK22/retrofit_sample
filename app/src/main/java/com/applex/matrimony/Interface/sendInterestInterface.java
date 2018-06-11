package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface sendInterestInterface {

    @GET("app/app_send_interest.php")
    Call<CommonParentPojo> doGetListResources(@Query("matrimony_id_from") String matrimony_id_from, @Query("matrimony_id_to") String matrimony_id_to);


}
