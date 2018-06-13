package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoProfile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getHighlightedInterface {

    @GET("app/app_get_highlighted.php")
    Call<ParentPojoProfile> doGetListResources();


}
