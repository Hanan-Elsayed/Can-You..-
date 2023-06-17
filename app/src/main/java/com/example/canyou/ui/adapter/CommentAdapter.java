package com.example.canyou.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.canyou.R;
import com.example.canyou.pojo.CommentResponseItem;
import com.example.canyou.pojo.SearchResponseItem;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<CommentResponseItem> comments;

    private OnItemClick onItemClick;

    public CommentAdapter() {
        comments = new ArrayList<>();
    }

    public void setComments(List<CommentResponseItem> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public  CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_layout, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        CommentResponseItem comment = comments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private ImageView userProfileImage;
        private TextView content;
        private Button deleteButton;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            userProfileImage = itemView.findViewById(R.id.user_profile_image);
            content = itemView.findViewById(R.id.comment_content);
            deleteButton=itemView.findViewById(R.id.delete_btn);
        }

        public void bind(CommentResponseItem comment) {
            // Load user profile image
            Glide.with(itemView)
                    .load(R.drawable.defaultimage)
                    .into(userProfileImage);

            content.setText(comment.getContent());
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onClick(comment.getId());
                }
            });
        }
    }


    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onClick(String commentId);
    }
}