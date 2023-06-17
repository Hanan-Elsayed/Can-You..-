package com.example.canyou.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

	@SerializedName("user")
	private CurrentUser currentUser;

	@SerializedName("token")
	private String token;

	public void setUser(CurrentUser currentUser){
		this.currentUser = currentUser;
	}

	public CurrentUser getUser(){
		return currentUser;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}