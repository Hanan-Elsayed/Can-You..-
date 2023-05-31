package com.example.canyou.pojo;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("AvatarUrl")
	private String avatarUrl;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("_id")
	private String id;

	@SerializedName("firstVisit")
	private boolean firstVisit;

	@SerializedName("email")
	private String email;

	public void setAvatarUrl(String avatarUrl){
		this.avatarUrl = avatarUrl;
	}

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setFirstVisit(boolean firstVisit){
		this.firstVisit = firstVisit;
	}

	public boolean isFirstVisit(){
		return firstVisit;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	// Convert User object to JSON string
	public String toJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	// Create User object from JSON string
	public static User fromJsonString(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, User.class);
	}

}