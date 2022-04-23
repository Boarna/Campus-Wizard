package com.example.the_campus_wizard.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.globals.general_functions.Intents;

public class UserProfile extends AppCompatActivity {
    private Button messagesBtn;
    private ImageView userProfileBackBtn;
    private Button readingListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        messagesBtn = findViewById(R.id.messagesBtn);
        userProfileBackBtn = findViewById(R.id.userProfileBackBtn);

        messagesBtn.setOnClickListener(v -> {
            Intents.chatDashboardIntent(this);
        });

        userProfileBackBtn.setOnClickListener(v -> {
            finish();
        });
    }
}
