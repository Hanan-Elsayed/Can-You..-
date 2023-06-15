package com.example.canyou;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.canyou.pojo.CreatePostRequest;
import com.example.canyou.pojo.CreatePostResponse;
import com.example.canyou.source.RetrofitClient;
import com.example.canyou.source.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private WebService webService;
    private MutableLiveData<String> messageLiveData;

    public PostRepository() {
        webService = RetrofitClient.getService();
        messageLiveData = new MutableLiveData<>();
    }

    public LiveData<String> getMessageLiveData() {
        return messageLiveData;
    }

    public void createPost(String token, String title, String imgBody) {
        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setTitle(title);
        createPostRequest.setImgBody(imgBody);

        // Add an authorization header with the bearer token
        String authorizationHeader = "Bearer " + token;

        webService.createPost(authorizationHeader, createPostRequest).enqueue(new Callback<CreatePostResponse>() {
            @Override
            public void onResponse(Call<CreatePostResponse> call, Response<CreatePostResponse> response) {
                if (response.isSuccessful()) {
                    messageLiveData.postValue("Post created successfully");
                } else {
                    messageLiveData.postValue("Failed to create post");
                }
            }

            @Override
            public void onFailure(Call<CreatePostResponse> call, Throwable t) {
                messageLiveData.postValue("Failed to create post: " + t.getMessage());
            }
        });
    }

}
