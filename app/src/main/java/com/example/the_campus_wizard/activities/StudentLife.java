package com.example.the_campus_wizard.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;

public class StudentLife extends AppCompatActivity {
    private ImageView studentLifeBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_life);


        studentLifeBackBtn = findViewById(R.id.studentLifeBackBtn);

        studentLifeBackBtn.setOnClickListener(v -> {
            finish();
        });
    }
}