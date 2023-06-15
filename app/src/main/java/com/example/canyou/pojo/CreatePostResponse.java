package com.example.canyou.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CreatePostResponse{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("comments")
	private List<Object> comments;

	@SerializedName("author")
	private Author author;

	@SerializedName("__v")
	private int v;

	@SerializedName("_id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("imgBody")
	private String imgBody;

	@SerializedName("likes")
	private List<Object> likes;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getCreatedAt(){
		return createdAt;
	}

	public List<Object> getComments(){
		return comments;
	}

	public Author getAuthor(){
		return author;
	}

	public int getV(){
		return v;
	}

	public String getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getImgBody(){
		return imgBody;
	}

	public List<Object> getLikes(){
		return likes;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}