package com.example.the_campus_wizard.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.holderClasses.TopicComment;
import com.google.firebase.database.annotations.NotNull;


import java.util.ArrayList;

public class forumCommentAdaptor extends RecyclerView.Adapter<forumCommentAdaptor.forumCommentViewHolder> {

    private ArrayList<TopicComment> comments_list;

    public forumCommentAdaptor(ArrayList<TopicComment> comments_list) {
        this.comments_list = comments_list;
    }

    @NotNull
    @Override
    public forumCommentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_comments_view, parent, false);
        return new forumCommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull forumCommentViewHolder holder, int position) {
        String username = comments_list.get(position).getUser_name();
        String text = comments_list.get(position).getText();
        String timestamp = comments_list.get(position).getTimestamp();

        holder.username.setText(username);
        holder.text.setText(text);
        holder.timestamp.setText(timestamp);

    }

    @Override
    public int getItemCount() {
        return comments_list.size();
    }

    public class forumCommentViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView text;
        TextView timestamp;

        public forumCommentViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.topicCommentUsername);
            text = itemView.findViewById(R.id.topicCommentText);
            timestamp = itemView.findViewById(R.id.topicCommentTimestamp);
        }
    }


}
