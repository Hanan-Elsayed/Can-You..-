package com.example.canyou.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("AvatarUrl")
    private String avatarUrl;
    private List<Object> followers;
    private List<Object> following;
    private String fullName;
    @SerializedName("_id")
    private String id;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public List<Object> getFollowers() {
        return followers;
    }

    public List<Object> getFollowing() {
        return following;
    }

    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return getFullName() + getAvatarUrl() + getId();
    }
}