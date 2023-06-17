package com.example.canyou.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.canyou.PreferenceManager;
import com.example.canyou.databinding.FragmentMyProfileBinding;
import com.example.canyou.pojo.CurrentUser;
import com.example.canyou.viewmodel.ProfileViewModel;


public class MyProfileFragment extends Fragment {

    private FragmentMyProfileBinding binding;

    private ProfileViewModel viewModel;

    public MyProfileFragment() {
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PreferenceManager preferenceManager = new PreferenceManager(requireContext());
        CurrentUser user = preferenceManager.getUser();
        String token = preferenceManager.getToken();
        if (user.getId() != null) {
            viewModel.getUserProfile(token, user.getId());
            viewModel.getProfile().observe(getViewLifecycleOwner(), response -> {
                Glide.with(this)
                        .load(response.getAvatarUrl())
                        .into(binding.userProfileImage);

                // Set the user's name
                binding.userFullName.setText(user.getFullName());
                if (response.getFollowing() != null) {
                    binding.textFollowingNumber.setText(String.valueOf(response.getFollowing().size()));
                }
                if (response.getFollowers() != null) {
                    binding.textFollowerNumber.setText(String.valueOf(response.getFollowers().size()));
                }
            });

        }
    }


}
