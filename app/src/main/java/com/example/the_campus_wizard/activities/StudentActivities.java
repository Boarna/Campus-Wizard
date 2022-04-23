package com.example.the_campus_wizard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.the_campus_wizard.R;

public class StudentActivities extends AppCompatActivity {

    private ImageView studentActivitiesBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_activities);


        studentActivitiesBackBtn = findViewById(R.id.studentActivitesBackBtn);

        studentActivitiesBackBtn.setOnClickListener(v -> {
            finish();
        });
    }
}