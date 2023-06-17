package com.example.canyou.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.canyou.Repository;
import com.example.canyou.pojo.LikeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<LikeResponse> _like;

    public LiveData<LikeResponse> getLike() {
        return _like;
    }

    private final Repository repository;

    public HomeViewModel() {
        repository = new Repository();
        _like = new MutableLiveData<>();
    }

    public void addLike(String token, String postId) {
        repository.addLike(token, postId, new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                if (response.isSuccessful())
                    _like.postValue(response.body());
                Log.d("KAMEL", response.message());
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {

            }
        });
    }
}
