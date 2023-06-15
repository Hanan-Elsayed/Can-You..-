package com.example.canyou.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.canyou.Repository;
import com.example.canyou.pojo.PostResponseItem;
import com.example.canyou.source.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    private MutableLiveData<List<PostResponseItem>> postsLiveData;

    private Repository repository;

    public MutableLiveData<String> message ;
    public PostViewModel() {
        repository = new Repository();
        postsLiveData = new MutableLiveData<>();
        message = new MutableLiveData<>();
    }
    public LiveData<List<PostResponseItem>> getPostsLiveData() {
        return postsLiveData;
    }

    public void fetchPosts(String token) {
        repository.getPosts(token, new Callback<List<PostResponseItem>>() {
            @Override
            public void onResponse(Call<List<PostResponseItem>> call, Response<List<PostResponseItem>> response) {
                if (response.isSuccessful()) {
                    List<PostResponseItem> posts = response.body();
                    postsLiveData.setValue(posts);
                } else {
                    // Handle error
                    message.setValue("Failed to fetch posts");
                }
            }

            @Override
            public void onFailure(Call<List<PostResponseItem>> call, Throwable t) {
                // Handle failure
                message.setValue(t.getLocalizedMessage());
            }
        });}
}
