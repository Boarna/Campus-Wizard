package com.example.the_campus_wizard.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.adaptors.forumSubjectsAdaptor;
import com.example.the_campus_wizard.globals.app_data_stores.AppSession;
import com.example.the_campus_wizard.holderClasses.ForumDiscussion;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ForumDiscussionSubjects extends AppCompatActivity {
    ArrayList<ForumDiscussion> subjects_list;
    private FirebaseFirestore db = AppSession.Session.db;
    private RecyclerView recyclerView;
    private ImageView forumSubjectsBackBtn;
    private TextView forumSubjectsTitle;
    private String forum_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_discussionsubjects);

        subjects_list = new ArrayList<>();
        recyclerView = findViewById(R.id.forumDisListRecyclerView);
        forumSubjectsTitle = findViewById(R.id.forumSubjectsTitle);

        forum_category = getIntent().getStringExtra("category");


        if (forum_category.equals("slife")) {
            forumSubjectsTitle.setText("Student life");
        } else if (forum_category.equals("olearning")) {
            forumSubjectsTitle.setText("Online learning");
        }

        forumSubjectsBackBtn = findViewById(R.id.forumSubjectsBackBtn);

        forumSubjectsBackBtn.setOnClickListener(v -> {
            finish();
        });

        populateList();
    }


    private void populateList() {
        db.getInstance().collection("forum").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                String discussion_author;
                String discussion_text;
                String db_category;
                String discussion_title;
                List<Map<Object, String>> comments;

                for (DocumentSnapshot result : task.getResult().getDocuments()
                ) {

                    String document_id = result.getId();
                    discussion_author = result.get("discussion_author").toString();
                    discussion_text = result.get("discussion_text").toString();
                    db_category = result.get("category").toString();
                    discussion_title = result.get("discussion_title").toString();
                    comments = (List<Map<Object, String>>) result.get("comments");

                    System.out.println(comments + " comments");
                    if (db_category.equals(forum_category)) {
                        subjects_list.add(new ForumDiscussion(discussion_author, discussion_text, comments, document_id, discussion_title));
                    }
                }
                setAdapter();
            }
        });
    }

    //Sets the adapter and recyclerView to display the queried list.
    private void setAdapter() {
        forumSubjectsAdaptor adapter = new forumSubjectsAdaptor(new ArrayList(subjects_list));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        System.out.println(adapter.getItemCount());
    }


}

