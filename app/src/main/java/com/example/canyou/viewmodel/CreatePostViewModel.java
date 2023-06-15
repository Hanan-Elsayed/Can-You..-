package com.example.canyou.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.canyou.PostRepository;

public class CreatePostViewModel extends ViewModel {
    private PostRepository postRepository;
    private LiveData<String> messageLiveData;

    public  CreatePostViewModel() {
        postRepository = new PostRepository();
        messageLiveData = postRepository.getMessageLiveData();
    }

    public LiveData<String> getMessageLiveData() {
        return messageLiveData;
    }

    public void createPost(String token, String title, String imgBody) {
        postRepository.createPost(token, title, imgBody);
    }
}