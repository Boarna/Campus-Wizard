package com.example.the_campus_wizard.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;

public class ForumTopic extends AppCompatActivity {


    private ImageView forumTopicBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_topic);


        forumTopicBackBtn.setOnClickListener(v -> finish());


    }


}
