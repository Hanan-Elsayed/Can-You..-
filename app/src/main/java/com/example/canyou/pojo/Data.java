package com.example.canyou.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("city")
	private String city;

	@SerializedName("isVerified")
	private boolean isVerified;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("posts")
	private List<Object> posts;

	@SerializedName("skills")
	private List<Object> skills;

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("requestsPost")
	private List<Object> requestsPost;

	@SerializedName("AvatarUrl")
	private String avatarUrl;

	@SerializedName("followers")
	private List<String> followers;

	@SerializedName("rate")
	private int rate;

	@SerializedName("following")
	private List<Object> following;

	@SerializedName("__v")
	private int V;

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

	public String getLastName(){
		return lastName;
	}

	public String getCity(){
		return city;
	}

	public boolean isIsVerified(){
		return isVerified;
	}

	public String getFullName(){
		return fullName;
	}

	public List<Object> getPosts(){
		return posts;
	}

	public List<Object> getSkills(){
		return skills;
	}

	public String getFirstName(){
		return firstName;
	}

	public List<Object> getRequestsPost(){
		return requestsPost;
	}

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public List<String> getFollowers(){
		return followers;
	}

	public int getRate(){
		return rate;
	}

	public List<Object> getFollowing(){
		return following;
	}

	public int getV(){
		return V;
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