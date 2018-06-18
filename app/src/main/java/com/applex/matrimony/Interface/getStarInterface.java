package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoMTongue;
import com.applex.matrimony.Pojo.ParentPojoRaasi;
import com.applex.matrimony.Pojo.ParentPojoStar;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getStarInterface {

    @GET("app/app_get_stars.php")
    Call<ParentPojoStar> doGetListResources();


}
