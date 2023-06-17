package com.example.canyou.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.canyou.Repository;
import com.example.canyou.pojo.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<UserResponse> _profile;

    public LiveData<UserResponse> getProfile() {
        return _profile;
    }

    private final Repository repository;

    public ProfileViewModel() {
        repository = new Repository();
        _profile = new MutableLiveData<>();
    }

    public void getUserProfile(String token, String authorId) {
        repository.getUserInformation(token, authorId, new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful())
                    _profile.postValue(response.body());
                Log.d("KAMEL", response.body().toString());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}
