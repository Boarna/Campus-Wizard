package com.example.the_campus_wizard.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;

public class Cyber extends AppCompatActivity {

    private ImageView cyberBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cyber_view);

        cyberBackBtn = findViewById(R.id.cyberBackBtn);

        cyberBackBtn.setOnClickListener(v -> {
            finish();
        });

    }
}
