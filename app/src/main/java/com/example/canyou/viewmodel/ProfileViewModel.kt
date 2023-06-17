package com.example.canyou.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.canyou.Repository
import com.example.canyou.pojo.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val repo: Repository) : ViewModel() {
    private val _profile = MutableLiveData<User>()
    val profile: LiveData<User> get() = _profile
    fun getUserProfile(token: String, authorId: String) {
        repo.getUserInformation(token, authorId, object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful)
                    _profile.postValue(response.body())
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}