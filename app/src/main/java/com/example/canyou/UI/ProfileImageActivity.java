package com.example.canyou.UI;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.canyou.PreferenceManager;
import com.example.canyou.R;
import com.example.canyou.databinding.ActivityProfileImageBinding;
import com.example.canyou.pojo.AvatarResponse;
import com.example.canyou.pojo.User;
import com.example.canyou.source.RetrofitClient;
import com.example.canyou.source.WebService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ProfileImageActivity extends AppCompatActivity {
    private static final int PERMISSION_CODE = 1234;
    private static final int CAPTURE_CODE = 1001;
    private ActivityProfileImageBinding binding;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private Uri imageUri;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<Intent> storagePermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_image);
        FirebaseApp.initializeApp(this);


       onClicks();

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        storagePermissionLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null && data.getData() != null) {
                                imageUri = data.getData();
                                binding.profileImage.setImageURI(imageUri);
                                try {
                                    Bitmap imageBit = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                                    saveCameraImage(imageBit);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(ProfileImageActivity.this, "Failed to load image", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            // Write access permission denied
                            Toast.makeText(ProfileImageActivity.this, "Write access permission denied", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }



    private void saveCameraImage(Bitmap imageBitmap) {
        // Generate a unique filename for the image
        String filename = UUID.randomUUID().toString() + ".jpg";

        // Get a reference to the desired location in Firebase Storage
        StorageReference imageRef = storageRef.child("images/" + filename);

        // Convert the image bitmap to bytes
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        // Upload the image to Firebase Storage
        UploadTask uploadTask = imageRef.putBytes(imageBytes);
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();

            // Get the download URL of the uploaded image
            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String imageUrl = uri.toString();

                // Call your API and pass the imageUrl to the backend
                sendImageUrl(imageUrl);
            }).addOnFailureListener(exception -> {
                Toast.makeText(this, "Failed to get image URL", Toast.LENGTH_SHORT).show();
            });
        }).addOnFailureListener(exception -> {
            Toast.makeText(this, "Failed to upload image", Toast.LENGTH_SHORT).show();
        });
    }


    private void sendImageUrl(String imageUrl) {
// Get the user token from SharedPreferences
        PreferenceManager preferenceManager = new PreferenceManager(this);
        String token ="eyJhbGciOiJIUzI1NiJ9.NjQ3NzFmODIxZjhiMTAyYzVlNDNkNjlj.OfLwzK1yj3Tl60bdul_06sfcIQfFVYBTp6IDolg5Bns";
                // preferenceManager.getToken();

        // Create a User object with the imageUrl
        User user = new User();
        if (imageUrl != null) {
            user.setAvatarUrl(imageUrl);
        } else {
            user.setAvatarUrl("https://cdn.pixabay.com/photo/2016/11/18/23/38/child-1837375_960_720.png");
        }

        // Create a Retrofit instance
        Retrofit retrofit = RetrofitClient.getRetrofit();

        // Create the web service interface
        WebService webService = retrofit.create(WebService.class);

        // Make the API call to update the user's avatar
        Call<AvatarResponse> call = webService.updateUser("Bearer " + token, user);
        call.enqueue(new Callback<AvatarResponse>() {
            @Override
            public void onResponse(Call<AvatarResponse> call, Response<AvatarResponse> response) {
                if (response.isSuccessful()) {
                    // Avatar update successful
                    Toast.makeText(ProfileImageActivity.this, "Avatar uploaded successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Avatar update failed
                    Toast.makeText(ProfileImageActivity.this, "Failed to upload avatar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AvatarResponse> call, Throwable t) {
                // Avatar update request failed
                Toast.makeText(ProfileImageActivity.this, "Failed to upload avatar", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void changeActivity() {
        Intent intent = new Intent(ProfileImageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void uploadDefaultPhoto() {
        // Set the default photo URL
        String defaultPhotoUrl = "https://cdn.pixabay.com/photo/2016/11/18/23/38/child-1837375_960_720.png";

        // Call your API and pass the defaultPhotoUrl to the backend
        sendImageUrl(defaultPhotoUrl);
    }
    void onClicks() {
        binding.skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDefaultPhoto();
                changeActivity();
            }
        });
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageUri != null) {
                    // The user has chosen a picture
                    changeActivity();
                } else {
                    // Prompt the user to choose a picture
                    Toast.makeText(ProfileImageActivity.this, "Please choose a profile picture or Skip", Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.addProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProfileUploadLayout();
            }
        });
    }

    private void getProfileUploadLayout() {
        ImageView camera_btn, gallery_btn, back_btn;
        Dialog uploadDialog = new Dialog(ProfileImageActivity.this);
        uploadDialog.setContentView(R.layout.profile_upload_layout);
        gallery_btn = uploadDialog.findViewById(R.id.gallery_button);
        camera_btn = uploadDialog.findViewById(R.id.camera_button);
        back_btn = uploadDialog.findViewById(R.id.back_button);

        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDialog.dismiss();
                 getProfileFromGallery();
            }
        });

        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDialog.dismiss();
                 getProfileFromCamera();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDialog.dismiss();
            }
        });

        uploadDialog.show();
        uploadDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        uploadDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        uploadDialog.getWindow().setGravity(Gravity.END);
    }

    private void getProfileFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        storagePermissionLauncher.launch(intent);
    }

    private void getProfileFromCamera() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                String[] permission = {Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, PERMISSION_CODE);
            }else {
                openCamera();
            }
        }else {

            openCamera();
        }
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore. Images.Media. TITLE, "new image");
        values.put(MediaStore. Images.Media.DESCRIPTION, "Fromthe Camera");
        imageUri= getContentResolver().insert(MediaStore. Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent camIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE); camIntent.putExtra (MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(camIntent, CAPTURE_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {

                Toast.makeText( this,  "Permission denied", Toast.LENGTH_SHORT).show();
                       }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==RESULT_OK){

            binding.profileImage.setImageURI(imageUri);
            try {
                Bitmap imageBit = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                saveCameraImage(imageBit);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(ProfileImageActivity.this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }

        }
    }
}