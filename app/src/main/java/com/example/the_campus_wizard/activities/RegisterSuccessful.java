package com.example.the_campus_wizard.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.globals.general_functions.Intents;

public class RegisterSuccessful extends AppCompatActivity {
    private ImageView successfulBackBtn;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_successfull);


        login = findViewById(R.id.succLoginBtn);

        successfulBackBtn = findViewById(R.id.successBackBtn);

        successfulBackBtn.setOnClickListener(v -> {
                    System.out.println("Wtf");
                    finish();
                }
        );


        login.setOnClickListener(v -> {
            startActivity(Intents.loginIntent(this));
        });


    }
}