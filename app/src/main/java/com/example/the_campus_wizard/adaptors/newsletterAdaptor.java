package com.example.the_campus_wizard.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.holderClasses.News;

import java.util.ArrayList;

public class newsletterAdaptor extends RecyclerView.Adapter<newsletterAdaptor.NewsletterViewHolder> {

    private ArrayList<News> news_list;

    public newsletterAdaptor(ArrayList<News> news_list) {
        this.news_list = news_list;
    }

    @NonNull
    @Override
    public newsletterAdaptor.NewsletterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_view, parent, false);

        return new NewsletterViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull newsletterAdaptor.NewsletterViewHolder holder, int position) {
        String title = news_list.get(position).getTitle();
        String topic = news_list.get(position).getTopic();

        holder.title.setText(title);
        holder.topic.setText(topic);
    }

    @Override
    public int getItemCount() {
        return news_list.size();
    }

    public class NewsletterViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView topic;

        public NewsletterViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.newsletterViewTitle);
            topic = itemView.findViewById(R.id.newsletterViewTopic);
        }
    }


}
