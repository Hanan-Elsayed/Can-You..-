package com.example.canyou.pojo;

import java.util.List;

public class UserResponse {
    private String AvatarUrl;
    private List<Object> followers;
    private List<Object> following;
    private String fullName;
    private String _id;

    public UserResponse(String avatarUrl, List<Object> followers, List<Object> following, String fullName, String _id) {
        AvatarUrl = avatarUrl;
        this.followers = followers;
        this.following = following;
        this.fullName = fullName;
        this._id = _id;
    }

    public String getAvatarUrl() {
        return AvatarUrl;
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

    public String get_id() {
        return _id;
    }

    public String getData() {
        return getFullName() + getAvatarUrl() + get_id();
    }
}