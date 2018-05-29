package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.ParentModelCountry;
import com.applex.matrimony.Pojo.ParentPojoReligion;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getReligionInterface {

    @GET("app/app_get_religions.php")
    Call<ParentPojoReligion> doGetListResources();

    /*@POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
}
