package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentPojoCurrency;
import com.applex.matrimony.Pojo.ParentPojoState;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface getCurrencyInterface {

    @FormUrlEncoded
    @POST("app/app_get_currency.php")
    Call<ParentPojoCurrency> doGetListResources(@Field("country") String country);

    /*@POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
}
