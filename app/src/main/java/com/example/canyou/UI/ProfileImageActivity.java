package com.example.canyou.UI;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.canyou.R;
import com.example.canyou.databinding.ActivityProfileImageBinding;
import com.google.firebase.FirebaseApp;
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
//    private FirebaseAuth auth;
//    private FirebaseUser firebaseUser;
//    private Uri imageUri;
//    private Dialog dialog;
//    private StorageTask uploadTask;
//    private ActivityResultLauncher<Intent> cameraLauncher;
//    private ActivityResultLauncher<Intent> storagePermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_image);
//        FirebaseApp.initializeApp(this);
//
//        onClicks();
//
//        auth = FirebaseAuth.getInstance();
//        firebaseUser = auth.getCurrentUser();
//        storagePermissionLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == Activity.RESULT_OK) {
//                            Intent data = result.getData();
//                            if (data != null && data.getData() != null) {
//                                imageUri = data.getData();
//                                binding.profileImage.setImageURI(imageUri);
//                                try {
//                                    Bitmap imageBit = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//                                    saveCameraImage(imageBit);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    Toast.makeText(ProfileImageActivity.this, "Failed to load image", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        } else {
//                            // Write access permission denied
//                            Toast.makeText(ProfileImageActivity.this, "Write access permission denied", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == Activity.RESULT_OK) {
//                            Intent data = result.getData();
//                            if (data != null && data.getExtras() != null) {
//                                Bitmap imageBit = (Bitmap) data.getExtras().get("data");
//                                binding.profileImage.setImageBitmap(imageBit);
//                                saveCameraImage(imageBit);
//                            }
//                        } else {
//                            // Write access permission denied
//                            Toast.makeText(ProfileImageActivity.this, "Write access permission denied", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
    }

//    private void saveCameraImage(Bitmap imageBitmap) {
//        if (imageBitmap == null) {
//            return;
//        }
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        byte[] imageBytes = byteArrayOutputStream.toByteArray();
//
//        String filename = UUID.randomUUID().toString() + ".jpg";
//
//        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
//        StorageReference imageRef = storageRef.child("images/" + filename);
//
//        UploadTask uploadTask = imageRef.putBytes(imageBytes);
//        uploadTask.addOnSuccessListener(taskSnapshot -> {
//            Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
//            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
//                String imageUrl = uri.toString();
//                // Call your API and pass the imageUrl to the backend
//                sendImageUrl(imageUrl);
//            }).addOnFailureListener(exception -> {
//                Toast.makeText(this, "Failed to get image URL", Toast.LENGTH_SHORT).show();
//            });
//        }).addOnFailureListener(exception -> {
//            Toast.makeText(this, "Failed to upload image", Toast.LENGTH_SHORT).show();
//        });
//    }

//    private void sendImageUrl(String imageUrl) {
//
//
//    }
//
//    public void changeActivity() {
//        Intent intent = new Intent(ProfileImageActivity.this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
//    private void uploadDefaultPhoto() {
//        // Get the default photo from your resources or assets
//        Bitmap defaultPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.defaultimage);
//
//        // Convert the Bitmap to a byte array
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        defaultPhoto.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        byte[] imageBytes = byteArrayOutputStream.toByteArray();
//
//        // Create a unique filename for the default photo
//        String filename = UUID.randomUUID().toString() + ".jpg";
//
//        // Get a reference to the Firebase Storage root
//        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
//
//        // Create a reference to the default photo file in Firebase Storage
//        StorageReference imageRef = storageRef.child("images/" + filename);
//
//        // Create an upload task to upload the default photo
//        UploadTask uploadTask = imageRef.putBytes(imageBytes);
//        uploadTask.addOnSuccessListener(taskSnapshot ->  {
//            // Default photo upload successful
//            Toast.makeText(this, "Default photo uploaded successfully", Toast.LENGTH_SHORT).show();
//
//            // Get the download URL of the uploaded default photo
//            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
//                String defaultPhotoUrl = uri.toString();
//
//                // Save the defaultPhotoUrl to your database
//                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
//                databaseRef.child("users").child(firebaseUser.getUid()).child("profilePhotoUrl").setValue(defaultPhotoUrl)
//                        .addOnCompleteListener(task -> {
//                            if (task.isSuccessful()) {
//                                // Default photo URL saved to database successfully
//                            } else {
//                                // Failed to save default photo URL to database
//                                Toast.makeText(this, "Failed to upload default photo", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }).addOnFailureListener(exception -> {
//                // Failed to get the download URL
//                Toast.makeText(this, "Failed to get default photo URL", Toast.LENGTH_SHORT).show();
//            });
//        }).addOnFailureListener(exception -> {
//            // Default photo upload failed
//            Toast.makeText(this, "Failed to upload default photo", Toast.LENGTH_SHORT).show();
//        });
//    }
//    void onClicks() {
//        binding.skipBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadDefaultPhoto();
//                changeActivity();
//            }
//        });
//        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (imageUri != null) {
//                    // The user has chosen a picture
//                    changeActivity();
//                    changeActivity();
//                } else {
//                    // Prompt the user to choose a picture
//                    Toast.makeText(ProfileImageActivity.this, "Please choose a profile picture or Skip", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        binding.addProfileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getProfileUploadLayout();
//            }
//        });
//    }
//
//    private void getProfileUploadLayout() {
//        ImageView camera_btn, gallery_btn, back_btn;
//        Dialog uploadDialog = new Dialog(this);
//        uploadDialog.setContentView(R.layout.profile_upload_layout);
//        uploadDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        gallery_btn = uploadDialog.findViewById(R.id.gallery_button);
//        camera_btn = uploadDialog.findViewById(R.id.camera_button);
//        back_btn = uploadDialog.findViewById(R.id.back_button);
//
//        gallery_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadDialog.dismiss();
//                getProfileFromGallery();
//            }
//        });
//
//        camera_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadDialog.dismiss();
//                getProfileFromCamera();
//            }
//        });
//
//        back_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadDialog.dismiss();
//            }
//        });
//
//        uploadDialog.show();
//        uploadDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        uploadDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        uploadDialog.getWindow().setGravity(Gravity.END);
//    }
//
//    private void getProfileFromGallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        storagePermissionLauncher.launch(intent);
//    }
//
//    private void getProfileFromCamera() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        cameraLauncher.launch(intent);
//    }
}