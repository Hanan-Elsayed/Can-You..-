package com.example.canyou.UI.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.canyou.R;
import com.example.canyou.pojo.SearchResponseItem;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<SearchResponseItem> users;

    public SearchAdapter() {
        users = new ArrayList<>();
    }

    public void setUsers(List<SearchResponseItem> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_layout, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        SearchResponseItem user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        private ImageView userProfileImage;
        private TextView userFullName;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            userProfileImage = itemView.findViewById(R.id.user_profile_image);
            userFullName = itemView.findViewById(R.id.user_full_name);
        }

        public void bind(SearchResponseItem user) {
            // Load user profile image
            Glide.with(itemView)
                    .load(user.getAvatarUrl())
                    .into(userProfileImage);

            userFullName.setText(user.getFullName());
        }
    }
}