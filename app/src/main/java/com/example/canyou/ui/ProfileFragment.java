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
import com.example.canyou.databinding.FragmentProfileBinding;
import com.example.canyou.viewmodel.ProfileViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel viewModel;
    private String authorId;
    private Bundle args;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        if (args != null) {
            authorId = args.getString("authorId");
        }
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PreferenceManager preferenceManager = new PreferenceManager(requireContext());
        String token = preferenceManager.getToken();
        if (authorId != null) {
            viewModel.getUserProfile(token, authorId);
            viewModel.getProfile().observe(getViewLifecycleOwner(), user -> {
                binding.userFullName.setText(user.getFullName());
                Glide.with(requireContext())
                        .load(user.getAvatarUrl())
                        .into(binding.userProfileImage);
                if (user.getFollowing() != null) {
                    binding.textFollowingNumber.setText(String.valueOf(user.getFollowing().size()));
                }
                if (user.getFollowers() != null) {
                    binding.textFollowerNumber.setText(String.valueOf(user.getFollowers().size()));
                }
                binding.btnFollow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.addFollow(token, user.getId());
                        int followers = Integer.parseInt(binding.textFollowerNumber.getText().toString());
                        binding.textFollowerNumber.setText(String.valueOf(++followers));
                        binding.btnFollow.setClickable(false);
                    }
                });
            });

        }
    }
}