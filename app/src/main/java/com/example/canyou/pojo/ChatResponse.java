package com.example.canyou.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ChatResponse{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("__v")
	private int v;

	@SerializedName("messages")
	private List<Object> messages;

	@SerializedName("_id")
	private String id;

	@SerializedName("users")
	private List<UsersItem> users;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getCreatedAt(){
		return createdAt;
	}

	public int getV(){
		return v;
	}

	public List<Object> getMessages(){
		return messages;
	}

	public String getId(){
		return id;
	}

	public List<UsersItem> getUsers(){
		return users;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}