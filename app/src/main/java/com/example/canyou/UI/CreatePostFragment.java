package com.example.canyou.UI;

import static android.app.Activity.RESULT_OK;

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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.canyou.PreferenceManager;
import com.example.canyou.R;
import com.example.canyou.databinding.FragmentCreatePostBinding;
import com.example.canyou.viewmodel.CreatePostViewModel;
import com.example.canyou.viewmodel.PostViewModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class CreatePostFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    private static final int PERMISSION_CODE = 1234;
    private static final int CAPTURE_CODE = 1001;
    private FragmentCreatePostBinding binding;
    private CreatePostViewModel postViewModel;
    private FirebaseAuth auth;
    private Uri imageUri;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    private ActivityResultLauncher<Intent> storagePermissionLauncher;
    private String imageUrll;
    private FirebaseUser firebaseUser;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(requireContext());
        auth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        firebaseUser = auth.getCurrentUser();

        storagePermissionLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null && data.getData() != null) {
                                imageUri = data.getData();
                                binding.PostImage.setImageURI(imageUri);
                                try {
                                    Bitmap imageBit = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                                    saveCameraImage(imageBit);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(requireContext(), "Failed to load image", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            // Write access permission denied
                            Toast.makeText(requireContext(), "Write access permission denied", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        postViewModel = new ViewModelProvider(this).get(CreatePostViewModel.class);

        // Set up click listeners

        binding.chooseImage.setOnClickListener(v -> getProfileUploadLayout());

        binding.submitBtn.setOnClickListener(v -> createPost());

        // Observe the message live data for post creation status
        postViewModel.getMessageLiveData().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
            if (message.equals("Post created successfully")) {

                navigateToHomeFragment();
            }
        });

        return view;
    }

    private void saveCameraImage(Bitmap imageBit) {
        // Generate a unique filename for the image
        String filename = UUID.randomUUID().toString() + ".jpg";

        // Get a reference to the desired location in Firebase Storage
        StorageReference imageRef = storageRef.child("images/" + filename);

        // Convert the image bitmap to bytes
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBit.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        // Upload the image to Firebase Storage
        UploadTask uploadTask = imageRef.putBytes(imageBytes);
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            Toast.makeText(requireContext(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();

            // Get the download URL of the uploaded image
            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String imageUrl = uri.toString();

                imageUrll=imageUrl;
            }).addOnFailureListener(exception -> {
                Toast.makeText(requireContext(), "Failed to get image URL", Toast.LENGTH_SHORT).show();
            });
        }).addOnFailureListener(exception -> {
            Toast.makeText(requireContext(), "Failed to upload image", Toast.LENGTH_SHORT).show();
        });

    }

    private void navigateToHomeFragment() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void getProfileUploadLayout() {
        ImageView camera_btn, gallery_btn, back_btn;
        Dialog uploadDialog = new Dialog(requireContext());
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

    private void getProfileFromCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                    ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, PERMISSION_CODE);
            } else {
                openCamera();
            }
        } else {
            openCamera();
        }
    }

    private void openCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore. Images.Media. TITLE, "new image");
        values.put(MediaStore. Images.Media.DESCRIPTION, "Fromthe Camera");
        imageUri= requireContext().getContentResolver().insert(MediaStore. Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent camIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE); camIntent.putExtra (MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(camIntent, CAPTURE_CODE);
    }

//    private void dispatchCaptureIntent() {
//        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (captureIntent.resolveActivity(requireContext().getPackageManager()) != null) {
//            startActivityForResult(captureIntent, REQUEST_IMAGE_CAPTURE);
//        } else {
//            Log.e("CreatePostFragment", "No camera app found");
//        }
//    }
    private void getProfileFromGallery() {
        // Check read storage permission
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        storagePermissionLauncher.launch(intent);
    }



//    private void dispatchSelectIntent() {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        startActivityForResult(intent, REQUEST_IMAGE_PICK);
//    }
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
        case PERMISSION_CODE:
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {

                Toast.makeText( requireContext(),  "Permission denied", Toast.LENGTH_SHORT).show();
            }
    }
}
    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==RESULT_OK){

            binding.PostImage.setImageURI(imageUri);
            try {
                Bitmap imageBit = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                saveCameraImage(imageBit);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(requireContext(), "Failed to load image", Toast.LENGTH_SHORT).show();
            }

        }
    }
    private void createPost() {
// Get the user token from SharedPreferences
        PreferenceManager preferenceManager = new PreferenceManager(requireContext());
        String token = preferenceManager.getToken();
        String title = binding.createPostET.getText().toString().trim();
        if (title.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }

//
//
//        // Create a reference to the Firebase Storage bucket
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference();
//
//        // Create a reference to the image file in Firebase Storage
//        String imageName = System.currentTimeMillis() + ".jpg";
//        StorageReference imageRef = storageRef.child("images/" + imageName);
//
//        // Upload the image file to Firebase Storage
//        UploadTask uploadTask = imageRef.putFile(Uri.fromFile(new File(selectedImagePath)));
//
//        // Register success and failure listeners for the upload task
//        uploadTask.addOnSuccessListener(taskSnapshot -> {
//            // Image upload successful
//            Toast.makeText(requireContext(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();
//
//            // Get the download URL of the uploaded image
//            imageRef.getDownloadUrl().addOnSuccessListener(downloadUrl -> {
//                // Use the download URL to create the post or save it to a database
//                String imageUrl = downloadUrl.toString();
        String imageUrl=imageUrll;

                // Call the createPost API with the image URL
                postViewModel.createPost( token, title, imageUrl);
//            }).addOnFailureListener(exception -> {
//                // Error occurred while retrieving the download URL
//                Toast.makeText(requireContext(), "Failed to retrieve download URL", Toast.LENGTH_SHORT).show();
//            });
//        }).addOnFailureListener(exception -> {
//            // Image upload failed
//            Toast.makeText(requireContext(), "Image upload failed", Toast.LENGTH_SHORT).show();
//        });
    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == REQUEST_IMAGE_CAPTURE) {
//                // Handle captured image
//                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
//                // Save the image to a file or perform further processing
//                Uri selectedImageUri = data.getData();
//                selectedImagePath = selectedImageUri.getPath();
//
//            } else if (requestCode == REQUEST_IMAGE_PICK) {
//                // Handle selected image
//                Uri selectedImageUri = data.getData();
//                if (selectedImageUri != null) {
//                    try {
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), selectedImageUri);
//                        // Save the image to a file or perform further processing
//
//                        // Update the selectedImagePath
//                        selectedImagePath = selectedImageUri.getPath();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_IMAGE_CAPTURE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                dispatchCaptureIntent();
//            } else {
//                Toast.makeText(requireContext(), "Camera permission denied", Toast.LENGTH_SHORT).show();
//            }
//        } else if (requestCode == REQUEST_IMAGE_PICK) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                dispatchSelectIntent();
//            } else {
//                Toast.makeText(requireContext(), "Storage permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


}