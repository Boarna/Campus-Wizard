package com.example.the_campus_wizard.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;

public class Timetable extends AppCompatActivity {
    private ImageView timetableBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_view);

        timetableBackBtn = findViewById(R.id.timetableBackBtn);


        timetableBackBtn.setOnClickListener(v -> {
            finish();
        });

    }
}
