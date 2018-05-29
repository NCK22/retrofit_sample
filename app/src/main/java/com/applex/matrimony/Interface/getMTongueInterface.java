package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoMTongue;
import com.applex.matrimony.Pojo.ParentPojoReligion;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getMTongueInterface {

    @GET("app/app_get_mothertongues.php")
    Call<ParentPojoMTongue> doGetListResources();


}
