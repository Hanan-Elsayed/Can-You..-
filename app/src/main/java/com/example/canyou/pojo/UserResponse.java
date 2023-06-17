package com.example.canyou.pojo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("AvatarUrl")
    private String avatarUrl;

    @SerializedName("followers")
    private List<Object> followers;

    @SerializedName("following")
    private List<Object> following;

    @SerializedName("fullName")
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
}