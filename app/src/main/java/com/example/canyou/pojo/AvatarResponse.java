package com.example.canyou.pojo;

import com.google.gson.annotations.SerializedName;

public class AvatarResponse{

	@SerializedName("user")
	private CurrentUser currentUser;

	public void setUser(CurrentUser currentUser){
		this.currentUser = currentUser;
	}

	public CurrentUser getUser(){
		return currentUser;
	}
}