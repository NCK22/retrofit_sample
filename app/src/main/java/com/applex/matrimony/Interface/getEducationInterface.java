package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoEducation;
import com.applex.matrimony.Pojo.ParentPojoHeight;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getEducationInterface {

    @GET("app/app_get_educations.php")
    Call<ParentPojoEducation> doGetListResources();


}
