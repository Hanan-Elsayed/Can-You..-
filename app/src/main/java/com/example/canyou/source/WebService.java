package com.example.canyou.source;
import com.example.canyou.pojo.AvatarResponse;
import com.example.canyou.pojo.LoginRequest;
import com.example.canyou.pojo.LoginResponse;
import com.example.canyou.pojo.SignUpRequest;
import com.example.canyou.pojo.SignUpResponse;
import com.example.canyou.pojo.User;

import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.Call;
import retrofit2.http.PUT;

public interface WebService {
    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
@POST("auth/register")
    Call<SignUpResponse> registerUser(@Body SignUpRequest signUpRequest);


    @PUT("avatar") //  for updating the user's avatar
    Call<AvatarResponse> updateUser(@Header("Authorization") String token, @Body User user);
}
