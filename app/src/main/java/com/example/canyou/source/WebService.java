package com.example.canyou.source;
import com.example.canyou.pojo.AvatarResponse;
import com.example.canyou.pojo.CreatePostRequest;
import com.example.canyou.pojo.CreatePostResponse;
import com.example.canyou.pojo.LoginRequest;
import com.example.canyou.pojo.LoginResponse;
import com.example.canyou.pojo.PostResponseItem;
import com.example.canyou.pojo.SearchResponseItem;
import com.example.canyou.pojo.SignUpRequest;
import com.example.canyou.pojo.SignUpResponse;
import com.example.canyou.pojo.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface WebService {

    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
@POST("auth/register")
    Call<SignUpResponse> registerUser(@Body SignUpRequest signUpRequest);


    @PATCH("api/user/img") //  for updating the user's avatar
    Call<AvatarResponse> updateUser(@Header("Authorization") String token, @Body User user);

@GET("api/posts")
  Call<List<PostResponseItem>> getPosts(@Header("Authorization") String token);
    @POST("api/posts")
    Call<CreatePostResponse> createPost(@Header("Authorization") String token, @Body CreatePostRequest createPostRequest);

    @GET("api/user/search")
    Call<List<SearchResponseItem>> searchUsers(@Header("Authorization") String token, @Query("searchValue") String searchValue);
}



