package com.example.the_campus_wizard.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.holderClasses.TopicComment;

import java.util.ArrayList;

public class topicCommentAdaptor extends RecyclerView.Adapter<topicCommentAdaptor.topicViewHolder> {

    private ArrayList<TopicComment> arr_list = new ArrayList<>();

    @NonNull
    @Override
    public topicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_comments_view, parent, false);

        return new topicCommentAdaptor.topicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull topicViewHolder holder, int position) {
        String text = arr_list.get(position).getText();
        String username = arr_list.get(position).getUser_name();
        String timestamp = arr_list.get(position).getTimestamp();

        holder.text.setText(text);
        holder.userName.setText(username);
        holder.timestamp.setText(timestamp);
    }

    @Override
    public int getItemCount() {
        return arr_list.size();
    }

    public class topicViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        TextView userName;
        TextView timestamp;

        public topicViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.topicCommentText);
            userName = itemView.findViewById(R.id.topicCommentUsername);
            timestamp = itemView.findViewById(R.id.topicCommentTimestamp);
        }
    }


}
