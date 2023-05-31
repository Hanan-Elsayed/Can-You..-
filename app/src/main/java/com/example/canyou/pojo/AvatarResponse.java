package com.example.canyou.pojo;

import com.google.gson.annotations.SerializedName;

public class AvatarResponse{

	@SerializedName("user")
	private User user;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}
}