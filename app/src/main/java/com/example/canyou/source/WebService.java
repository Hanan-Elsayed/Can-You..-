package com.example.canyou.source;

import com.example.canyou.pojo.AvatarResponse;
import com.example.canyou.pojo.ChatResponse;
import com.example.canyou.pojo.CommentResponse;
import com.example.canyou.pojo.CreatePostRequest;
import com.example.canyou.pojo.CreatePostResponse;
import com.example.canyou.pojo.CurrentUser;
import com.example.canyou.pojo.FollowResponse;
import com.example.canyou.pojo.LikeResponse;
import com.example.canyou.pojo.LoginRequest;
import com.example.canyou.pojo.LoginResponse;
import com.example.canyou.pojo.PostResponseItem;
import com.example.canyou.pojo.SearchResponseItem;
import com.example.canyou.pojo.SignUpRequest;
import com.example.canyou.pojo.SignUpResponse;
import com.example.canyou.pojo.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {

    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("auth/signUp")
    Call<SignUpResponse> registerUser(@Body SignUpRequest signUpRequest);

    @PATCH("api/user/img")
        //  for updating the user's avatar
    Call<AvatarResponse> updateUser(@Header("Authorization") String token, @Body CurrentUser user);

    @GET("api/posts")
    Call<List<PostResponseItem>> getPosts(@Header("Authorization") String token);

    @GET("api/user/{userId}")
    Call<UserResponse> getUserInformation(@Header("Authorization") String token,
                                          @Path("userId") String userId);

    @GET("api/chat//{userId}")
    Call<ChatResponse> getUserItem(@Header("Authorization") String token,
                                   @Path("userId") String userId);
    @GET("api/posts/post/{postId}/comments")
    Call<CommentResponse> getComments(@Header("Authorization") String token,
                                      @Path("postId") String commentId);
    @PUT("api/posts/post/{postId}/like")
    Call<LikeResponse> addLike(@Header("Authorization") String token,
                               @Path("postId") String postId);

    @PUT("api/user/follow/{userId}")
    Call<FollowResponse> addFollow(@Header("Authorization") String token,
                                   @Path("userId") String userId);

    @POST("api/posts")
    Call<CreatePostResponse> createPost(@Header("Authorization") String token, @Body CreatePostRequest createPostRequest);

    @GET("api/user/search")
    Call<List<SearchResponseItem>> searchUsers(@Header("Authorization") String token, @Query("searchValue") String searchValue);
}



