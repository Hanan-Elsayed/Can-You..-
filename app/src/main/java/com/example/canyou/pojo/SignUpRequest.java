package com.example.canyou.pojo;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest{

	@SerializedName("birthDay")
	private String birthDay;

	@SerializedName("password")
	private String password;

	@SerializedName("phoneNumber")
	private String phoneNumber;

	@SerializedName("gender")
	private String gender;

	@SerializedName("city")
	private String city;

	@SerializedName("NationalID")
	private String nationalID;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("ConfirmPassword")
	private String confirmPassword;

	@SerializedName("email")
	private String email;

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDay(){
		return birthDay;
	}

	public String getPassword(){
		return password;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public String getGender(){
		return gender;
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

	public String getConfirmPassword(){
		return confirmPassword;
	}

	public String getEmail(){
		return email;
	}
}