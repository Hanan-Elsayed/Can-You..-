package com.example.canyou.pojo;

import com.google.gson.annotations.SerializedName;

public class CreatePostRequest{

	@SerializedName("title")
	private String title;

	@SerializedName("imgBody")
	private String imgBody;

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setImgBody(String imgBody){
		this.imgBody = imgBody;
	}

	public String getImgBody(){
		return imgBody;
	}
}