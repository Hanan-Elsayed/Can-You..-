package com.example.canyou.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.canyou.PreferenceManager;
import com.example.canyou.R;
import com.example.canyou.databinding.FragmentCreatePostBinding;
import com.example.canyou.databinding.FragmentProfileBinding;
import com.example.canyou.pojo.User;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;


    public ProfileFragment() {
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
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        PreferenceManager preferenceManager = new PreferenceManager(requireContext());
        User user = preferenceManager.getUser();
        if (user != null) {
            binding.userFullName.setText(user.getFullName());
            Glide.with(requireContext())
                    .load(user.getAvatarUrl())
                    .into(binding.userProfileImage);
        }
        return view;

    }

}