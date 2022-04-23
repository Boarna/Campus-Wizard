package com.example.the_campus_wizard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.globals.general_functions.Intents;

public class Modules extends AppCompatActivity {

    private ImageView modulesBackBtn;
    private Button modulesGoCyberBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modules_view);

        modulesBackBtn = findViewById(R.id.modulesBackBtn);
        modulesGoCyberBtn = findViewById(R.id.modulesGoCyberBtn);

        Intent ModulesIntent = Intents.CyberIntent(this);

        modulesGoCyberBtn.setOnClickListener(v -> {
                startActivity(ModulesIntent);
        });


        modulesBackBtn.setOnClickListener(v -> {
            finish();
        });

    }
}
