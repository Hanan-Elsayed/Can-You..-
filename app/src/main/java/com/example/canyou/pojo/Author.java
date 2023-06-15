package com.example.canyou.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Author{

	@SerializedName("birthDay")
	private String birthDay;

	@SerializedName("city")
	private String city;

	@SerializedName("NationalID")
	private String nationalID;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("skills")
	private List<Object> skills;

	@SerializedName("AvatarUrl")
	private String avatarUrl;

	@SerializedName("followers")
	private List<Object> followers;

	@SerializedName("requestsPost")
	private List<Object> requestsPost;

	@SerializedName("rate")
	private int rate;

	@SerializedName("following")
	private List<Object> following;

	@SerializedName("__v")
	private int v;

	@SerializedName("chats")
	private List<Object> chats;

	@SerializedName("_id")
	private String id;

	@SerializedName("email")
	private String email;

	public String getBirthDay(){
		return birthDay;
	}

	public String getCity(){
		return city;
	}

	public String getNationalID(){
		return nationalID;
	}

	public String getFullName(){
		return fullName;
	}

	public List<Object> getSkills(){
		return skills;
	}

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public List<Object> getFollowers(){
		return followers;
	}

	public List<Object> getRequestsPost(){
		return requestsPost;
	}

	public int getRate(){
		return rate;
	}

	public List<Object> getFollowing(){
		return following;
	}

	public int getV(){
		return v;
	}

	public List<Object> getChats(){
		return chats;
	}

	public String getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}
}