package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentPojoEducation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface shortListInterface {

    @GET("app/app_shortlist.php")
    Call<CommonParentPojo> doGetListResources(@Query("matrimony_id_from") String matrimony_id_from,@Query("matrimony_id_to") String matrimony_id_to);


}
