package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoGotra;
import com.applex.matrimony.Pojo.ParentPojoStar;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getGotraInterface {

    @GET("app/app_get_gotra.php")
    Call<ParentPojoGotra> doGetListResources();


}
