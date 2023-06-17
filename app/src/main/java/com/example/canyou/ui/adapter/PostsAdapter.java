package com.example.canyou.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.canyou.R;
import com.example.canyou.databinding.ItemPostLayoutBinding;
import com.example.canyou.pojo.Author;
import com.example.canyou.pojo.PostResponseItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//adapter class to bind the posts data to the RecyclerView in the home fragment
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
    private List<PostResponseItem> posts;
    private OnItemClick onItemClick;

    @SuppressLint("NotifyDataSetChanged")
    public void setPosts(List<PostResponseItem> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        // Bind the post data to the views
        PostResponseItem post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts != null ? posts.size() : 0;
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        private ItemPostLayoutBinding binding;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemPostLayoutBinding.bind(itemView);
        }

        public void bind(PostResponseItem post) {
            // Bind the post data to the views
            binding.postTitle.setText(post.getTitle());
            post.getId();
            binding.likesCount.setText(String.valueOf(post.getLikes().size()));
            String originalDateString = post.getCreatedAt();

            // Define the input format of the date string
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

            // Define the desired output format
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy, HH:mm");

            try {
                // Parse the original date string
                Date originalDate = originalFormat.parse(originalDateString);

                // Format the date to the desired output format
                String formattedDate = outputFormat.format(originalDate);

                // Display the formatted date
                binding.postCreatedAt.setText(formattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Author author = post.getAuthor();
            binding.authorFullName.setText(author.getFullName());
            Glide.with(itemView)
                    .load(author.getAvatarUrl())
                    .into(binding.authorProfileImage);
            binding.authorProfileImage.setOnClickListener(view -> {
                onItemClick.onClick(author.getId());
            });
            binding.likeCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onLike(post.getId());
                    int likes = Integer.parseInt(binding.likesCount.getText().toString());
                    binding.likesCount.setText(String.valueOf(++likes));
                }
            });
            binding.likesCount.setText(String.valueOf(post.getLikes().size()));


            Glide.with(itemView)
                    .load(post.getImgBody())
                    .into(binding.postImg);

        }
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onClick(String authorId);

        void onLike(String postId);
    }

}