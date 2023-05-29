package com.example.canyou.UI;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;



import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.canyou.R;
import com.example.canyou.databinding.ActivityLoginBinding;
import com.example.canyou.databinding.ActivityProfileImageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class ProfileImageActivity extends AppCompatActivity {
    private ActivityProfileImageBinding binding;
//    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private Uri imageUri;
    private Dialog dialog;
//    private String myUri ="";
    private StorageTask uploadTask;
//    private StorageReference storageProfilePicRef;
    private ActivityResultLauncher<Intent> cameraLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_image);

        onClicks();
//init
        auth=FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        dialog=new Dialog(this);
        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result != null) {
                            imageUri = result.getData().getData();
                            binding.profileImage.setImageURI(imageUri);
                            Bitmap imageBit = (Bitmap) result.getData().getExtras().get("data");
                            binding.profileImage.setImageBitmap(imageBit);
                            saveCameraImage(imageBit);
                        }
                    }
                });
    }
    private void saveCameraImage(Bitmap imageBitmap) {
        // Check if the imageBitmap is not null
        if (imageBitmap == null) {
            return;
        }

        // Convert the Bitmap to a byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        // Create a unique filename for the image
        String filename = UUID.randomUUID().toString() + ".jpg";

        // Get a reference to the Firebase Storage root
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        // Create a reference to the image file in Firebase Storage
        StorageReference imageRef = storageRef.child("images/" + filename);

        // Create an upload task to upload the image
        UploadTask uploadTask = imageRef.putBytes(imageBytes);
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // Image upload successful
            Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();

            // Get the download URL of the uploaded image
            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String imageUrl = uri.toString();

                // TODO: Save the imageUrl to your database or use it as needed
                // You can update your database or preferences with the imageUrl

            }).addOnFailureListener(exception -> {
                // Failed to get the download URL
                Toast.makeText(this, "Failed to get image URL", Toast.LENGTH_SHORT).show();
            });
        }).addOnFailureListener(exception -> {
            // Image upload failed
            Toast.makeText(this, "Failed to upload image", Toast.LENGTH_SHORT).show();
        });
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
        binding.addProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProfileUploadLayout();
            }
        });
    }

    private void getProfileUploadLayout() {
        ImageView camera_btn, gallery_btn, back_btn;
        Dialog uploadDialog = new Dialog(this);
        uploadDialog.setContentView(R.layout.profile_upload_layout);
        uploadDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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

        // Show the dialog
        uploadDialog.show();

        uploadDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        uploadDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        uploadDialog.getWindow().setGravity(Gravity.END);
    }

    private void getProfileFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        cameraLauncher.launch(intent);
    }

    private void getProfileFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraLauncher.launch(intent);
    }
}