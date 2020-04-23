package com.everton.appposts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private ArrayList<Post> listPosts;
    private Context context;

    public PostAdapter(ArrayList<Post> listPosts, Context context) {
        this.listPosts = listPosts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = listPosts.get(position);
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return listPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title);
            body = itemView.findViewById(R.id.tv_body);
        }
    }
}
