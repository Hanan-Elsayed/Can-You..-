package com.example.canyou.source;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public abstract class   RetrofitClient {
    private final static  String BASE_URL="https://canyou.onrender.com/";
    private static Retrofit retrofit;
    private static HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
    private static OkHttpClient okHttpClient;
    public static WebService getService(){
httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        if(retrofit==null)
        {
            retrofit= new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(WebService.class);
    }

}
