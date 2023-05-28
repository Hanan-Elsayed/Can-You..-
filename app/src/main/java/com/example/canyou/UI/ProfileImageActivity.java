package com.example.canyou.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.canyou.R;
import com.example.canyou.databinding.ActivityLoginBinding;
import com.example.canyou.databinding.ActivityProfileImageBinding;

public class ProfileImageActivity extends AppCompatActivity {
    private ActivityProfileImageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile_image);
    }
}