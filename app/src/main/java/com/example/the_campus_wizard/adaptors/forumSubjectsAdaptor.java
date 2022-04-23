package com.example.the_campus_wizard.adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.globals.general_functions.Intents;
import com.example.the_campus_wizard.holderClasses.ForumDiscussion;
import com.google.firebase.database.annotations.NotNull;


import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class forumSubjectsAdaptor extends RecyclerView.Adapter<forumSubjectsAdaptor.forumSubjectsViewHolder> {

    private ArrayList<ForumDiscussion> comments_list;

    public forumSubjectsAdaptor(ArrayList<ForumDiscussion> comments_list) {
        this.comments_list = comments_list;
    }

    @NonNull
    @Override
    public forumSubjectsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_discussionsubjects_view, parent, false);
        return new forumSubjectsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull forumSubjectsViewHolder holder, int position) {
        String author_name = comments_list.get(position).getAuthor_name();
        String discussion = comments_list.get(position).getDiscussion();
        String document_id = comments_list.get(position).getDocument_id();
        String title = comments_list.get(position).getTitle();

        holder.authorName.setText(author_name);
        holder.title.setText(title);

        holder.itemView.setOnClickListener(view -> {
            Context context = view.getContext();
            Intent intent = Intents.forumTopicIntent(context);

            intent.putExtra("author_name", author_name);
            intent.putExtra("discussion", discussion);
            intent.putExtra("document_id", document_id);
            intent.putExtra("title", title);

            startActivity(context, intent, null);
        });
    }

    @Override
    public int getItemCount() {
        return comments_list.size();
    }


    public class forumSubjectsViewHolder extends RecyclerView.ViewHolder {
        TextView authorName;
        TextView title;

        public forumSubjectsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.forumTopicName);
            title = itemView.findViewById(R.id.forumTopicText);
        }
    }
}
