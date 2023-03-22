package com.example.canyou.source;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class  RetrofitClient {
    private final static  String BASE_URL="https://canyou.onrender.com/";
    private static Retrofit retrofit;
    public static WebService getService(){

        if(retrofit==null)
        {
            retrofit= new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(WebService.class);
    }

}
