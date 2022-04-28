package com.example.the_campus_wizard.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;

public class Activities extends AppCompatActivity {

    private ImageView activitiesBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        activitiesBackBtn = findViewById(R.id.activitiesBackBtn);

        activitiesBackBtn.setOnClickListener(v -> {
            finish();
        });
    }

}

