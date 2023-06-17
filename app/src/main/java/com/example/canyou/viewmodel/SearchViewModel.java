package com.example.canyou.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.canyou.pojo.SearchResponseItem;
import com.example.canyou.source.RetrofitClient;
import com.example.canyou.source.WebService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {
    private MutableLiveData<List<SearchResponseItem>> searchResults;
    private MutableLiveData<String> messageLiveData = new MutableLiveData<>();

    public LiveData<List<SearchResponseItem>> getSearchResults() {
        if (searchResults == null) {
            searchResults = new MutableLiveData<>();
        }
        return searchResults;
    }

    public LiveData<String> getMessageLiveData() {
        return messageLiveData;
    }

    public void searchUsers(String token, String searchValue) {
        WebService webService = RetrofitClient.getService();

        String authorizationHeader = "Bearer " + token;
        Call<List<SearchResponseItem>> call = webService.searchUsers(authorizationHeader, searchValue);

        call.enqueue(new Callback<List<SearchResponseItem>>() {
            @Override
            public void onResponse(Call<List<SearchResponseItem>> call, Response<List<SearchResponseItem>> response) {
                if (response.isSuccessful()) {
                    List<SearchResponseItem> users = response.body();
                    searchResults.postValue(users);
                } else {
                    String errorMessage = "Failed to search";
                    if (response.errorBody() != null) {
                        try {
                            errorMessage = response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    messageLiveData.postValue(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<List<SearchResponseItem>> call, Throwable t) {
                String errorMessage = "Failed to search: " + t.getMessage();
                messageLiveData.postValue(errorMessage);
            }
        });
    }

}
