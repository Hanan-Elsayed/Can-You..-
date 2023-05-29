package com.example.canyou.source;
import com.example.canyou.pojo.LoginRequest;
import com.example.canyou.pojo.LoginResponse;
import com.example.canyou.pojo.SignUpRequest;
import com.example.canyou.pojo.SignUpResponse;

import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.Call;

public interface WebService {
    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
@POST("auth/register")
    Call<SignUpResponse> registerUser(@Body SignUpRequest signUpRequest);
}
