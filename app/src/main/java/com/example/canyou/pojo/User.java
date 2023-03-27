package com.example.canyou.pojo;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("AvatarUrl")
	private String avatarUrl;

	@SerializedName("city")
	private String city;

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

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
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
}