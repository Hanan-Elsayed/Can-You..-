package com.example.canyou.source;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public abstract class   RetrofitClient {

    public static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit= new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://canyou.onrender.com/")
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    public static WebService getService(){
WebService webService=getRetrofit().create(WebService.class);
return webService;
    }

}
