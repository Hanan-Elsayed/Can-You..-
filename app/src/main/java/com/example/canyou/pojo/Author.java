package com.example.canyou.pojo;

import com.google.gson.annotations.SerializedName;

public class Author{

	@SerializedName("AvatarUrl")
	private String avatarUrl;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("_id")
	private String id;

	@SerializedName("email")
	private String email;

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public String getFullName(){
		return fullName;
	}

	public String getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}
}