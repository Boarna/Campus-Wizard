package com.example.the_campus_wizard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.globals.general_functions.Intents;

public class Forum extends AppCompatActivity {

    private Button studentLifeBtn;
    private Button onlineLearningBtn;
    private ImageView forumMainBackBtn;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        forumMainBackBtn = findViewById(R.id.forumMainBackBtn);
        studentLifeBtn = findViewById(R.id.forumStudentLifeBtn);
        onlineLearningBtn = findViewById(R.id.forumOnlineLearningBtn);


        forumMainBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = Intents.forumDiscussionSubjectsIntent(this);
        studentLifeBtn.setOnClickListener(v -> {
            String category = "slife";
            intent.putExtra("category", category);
            startActivity(intent);
        });

        onlineLearningBtn.setOnClickListener(v -> {
            String category = "olearning";
            intent.putExtra("category", category);
            startActivity(intent);
        });
    }
}