package com.example.canyou.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CommentResponseItem{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("__v")
	private int v;

	@SerializedName("_id")
	private String id;

	@SerializedName("postId")
	private String postId;

	@SerializedName("content")
	private String content;

	@SerializedName("likes")
	private List<Object> likes;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getCreatedAt(){
		return createdAt;
	}

	public int getV(){
		return v;
	}

	public String getId(){
		return id;
	}

	public String getPostId(){
		return postId;
	}

	public String getContent(){
		return content;
	}

	public List<Object> getLikes(){
		return likes;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}