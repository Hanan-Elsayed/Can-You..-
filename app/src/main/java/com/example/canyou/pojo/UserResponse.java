package com.example.canyou.pojo;

import java.util.List;

public class UserResponse {
    private String AvatarUrl;
    private List<Object> followers;
    private List<Object> following;
    private String fullName;
    private String _id;

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

    public void setAvatarUrl(String avatarUrl) {
        this.AvatarUrl = avatarUrl;
    }

    public void setFollowers(List<Object> followers) {
        this.followers = followers;
    }

    public void setFollowing(List<Object> following) {
        this.following = following;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getData() {
        return getFullName() + getAvatarUrl() + get_id();
    }
}