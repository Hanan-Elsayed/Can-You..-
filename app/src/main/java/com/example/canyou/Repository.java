package com.example.canyou;

import com.example.canyou.pojo.PostResponseItem;
import com.example.canyou.source.RetrofitClient;
import com.example.canyou.source.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
//Repository class acts as a data source and interacts with the WebService to fetch the posts
public class Repository {

    private WebService webService;

    public Repository() {
        webService = RetrofitClient.getService();
    }

    public void getPosts(String token, Callback<List<PostResponseItem>> callback) {
        Call<List<PostResponseItem>> call = webService.getPosts("Bearer " + token);
        call.enqueue(callback);
    }
}
