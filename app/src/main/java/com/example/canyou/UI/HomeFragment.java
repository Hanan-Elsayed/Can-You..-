package com.example.canyou.UI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.canyou.PreferenceManager;
import com.example.canyou.R;
import com.example.canyou.UI.adapter.PostsAdapter;
import com.example.canyou.databinding.FragmentHomeBinding;
import com.example.canyou.pojo.PostResponseItem;
import com.example.canyou.source.RetrofitClient;
import com.example.canyou.viewmodel.PostViewModel;

import java.util.List;


public class HomeFragment extends Fragment {
    private PostViewModel postViewModel;
    private RecyclerView recyclerView;
    private PostsAdapter postsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postsAdapter = new PostsAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        recyclerView = binding.postsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(postsAdapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Observe the LiveData for posts
        postViewModel.getPostsLiveData().observe(getViewLifecycleOwner(), new Observer<List<PostResponseItem>>() {
            @Override
            public void onChanged(List<PostResponseItem> posts) {
                // Update the adapter with the new list of posts
                postsAdapter.setPosts(posts);
            }
        });
        postViewModel.message.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(requireContext(), "" + s, Toast.LENGTH_SHORT).show();
            }
        });
        // Fetch posts when the fragment is created
        // Get the user token from SharedPreferences
        PreferenceManager preferenceManager = new PreferenceManager(requireContext());
        String token = preferenceManager.getToken();

        postViewModel.fetchPosts(token);
        CardView createPostCV =view.findViewById(R.id.create_post_card_view);
        createPostCV.setOnClickListener(v -> navigateToCreatePostFragment());
    }

    private void navigateToCreatePostFragment() {
        CreatePostFragment createPostFragment = new CreatePostFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, createPostFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}


