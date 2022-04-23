package com.example.the_campus_wizard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.the_campus_wizard.R;

public class AcademicProfile extends AppCompatActivity {

    private ImageView academicProfileBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_profile);

        academicProfileBackBtn = findViewById(R.id.academicProfileBackBtn);

        academicProfileBackBtn.setOnClickListener(v -> {
            finish();
        });
    }
}