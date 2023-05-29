package com.example.canyou.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.canyou.R;
import com.example.canyou.databinding.ActivityLoginBinding;
import com.example.canyou.databinding.ActivityProfileImageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class ProfileImageActivity extends AppCompatActivity {
    private ActivityProfileImageBinding binding;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private Uri imageUri;
    private String myUri ="";
    private StorageTask uploadTask;
    private StorageReference storageProfilePicRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile_image);

        onClicks();
    }
    public void changeActivity() {
        Intent intent=new Intent(ProfileImageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    void onClicks(){
        binding.skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }

        });
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }

        });
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}