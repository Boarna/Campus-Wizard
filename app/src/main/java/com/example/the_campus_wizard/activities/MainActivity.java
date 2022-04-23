package com.example.the_campus_wizard.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.globals.general_functions.Intents;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        login = findViewById(R.id.mainLoginBtn);
        register = findViewById(R.id.mainRegisterBtn);

        login.setOnClickListener(v -> {
            startActivity(Intents.loginIntent(this));
        });

        register.setOnClickListener(v -> {
            startActivity(Intents.registerIntent(this));
        });

    }
}