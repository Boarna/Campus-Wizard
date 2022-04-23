package com.example.the_campus_wizard.adaptors;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class forumTopicAdaptor extends RecyclerView.Adapter<forumTopicAdaptor.TopicViewHolder> {


    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
