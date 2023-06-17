package com.example.canyou.pojo;

import com.google.gson.annotations.SerializedName;

public class FollowResponse{

	@SerializedName("data")
	private Data data;

	public Data getData(){
		return data;
	}
}