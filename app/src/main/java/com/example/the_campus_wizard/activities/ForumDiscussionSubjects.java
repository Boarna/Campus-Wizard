package com.example.the_campus_wizard.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.globals.app_data_stores.AppSession;
import com.google.firebase.firestore.FirebaseFirestore;

public class ForumDiscussionSubjects extends AppCompatActivity {
    private FirebaseFirestore db = AppSession.Session.db;
    private ImageView forumSubjectsBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_discussionsubjects);


        forumSubjectsBackBtn = findViewById(R.id.forumSubjectsBackBtn);

        forumSubjectsBackBtn.setOnClickListener(v -> {
            finish();
        });


    }
}

