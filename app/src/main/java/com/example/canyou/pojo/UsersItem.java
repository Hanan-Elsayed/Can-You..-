package com.example.canyou.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UsersItem{

	@SerializedName("birthDay")
	private String birthDay;

	@SerializedName("isVerified")
	private boolean isVerified;

	@SerializedName("city")
	private String city;

	@SerializedName("NationalID")
	private String nationalID;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("posts")
	private List<String> posts;

	@SerializedName("skills")
	private List<Object> skills;

	@SerializedName("password")
	private String password;

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

	@SerializedName("firstVisit")
	private boolean firstVisit;

	@SerializedName("email")
	private String email;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getBirthDay(){
		return birthDay;
	}

	public boolean isIsVerified(){
		return isVerified;
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

	public List<String> getPosts(){
		return posts;
	}

	public List<Object> getSkills(){
		return skills;
	}

	public String getPassword(){
		return password;
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

	public boolean isFirstVisit(){
		return firstVisit;
	}

	public String getEmail(){
		return email;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}