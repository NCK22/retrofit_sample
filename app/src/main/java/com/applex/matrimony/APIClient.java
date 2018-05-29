package com.applex.matrimony;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit retrofit=null;
    private static String BASE_URL="http://applex360.in/Deshpande-family/Matrimony-web/";

    public static Retrofit getClient()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
