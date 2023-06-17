package com.example.canyou.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.canyou.PreferenceManager;
import com.example.canyou.R;
import com.example.canyou.databinding.FragmentMyProfileBinding;
import com.example.canyou.databinding.FragmentProfileBinding;
import com.example.canyou.pojo.User;


public class MyProfileFragment extends Fragment {

    private FragmentMyProfileBinding binding;

    public MyProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    PreferenceManager preferenceManager = new PreferenceManager(requireContext());
    String token = preferenceManager.getToken();
    User user = preferenceManager.getUser();
        if (user != null) {
        Glide.with(this)
                .load(user.getAvatarUrl())
                .into(binding.userProfileImage);

        // Set the user's name
        binding.userFullName.setText(user.getFullName());
            if (user.getFollowing() != null) {
                binding.textFollowingNumber.setText(user.getFollowing().size());
            }
            if (user.getFollowers() != null) {
                binding.textFollowerNumber.setText(user.getFollowers().size());
            }
        }
    }
