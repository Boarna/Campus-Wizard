package com.example.the_campus_wizard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.globals.app_data_stores.AppSession;
import com.example.the_campus_wizard.globals.general_functions.Intents;

import java.util.Objects;

public class StudentDashboard extends AppCompatActivity {

    private Button timetable;
    private Button library;
    private Button forum;
    private TextView dtextview;
    private Button modules;
    private Button studentActivities;
    private Button academicProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        timetable = findViewById(R.id.dashboardTimetableBtn);
        library = findViewById(R.id.dashboardLibraryBtn);
        forum = findViewById(R.id.dashboardForumBtn);
        dtextview = findViewById(R.id.dTextWelcome);
        modules = findViewById(R.id.dashboardModulesBtn);
        academicProfile = findViewById(R.id.dashboardAcademicProfileBtn);
        studentActivities = findViewById(R.id.dashboardStudentActivitiesBtn);

        String first_name = "";

        try {
            first_name = Objects.requireNonNull(AppSession.Session.userData.get("first_name")).toString();

        } catch (Exception e) {

        }

        dtextview.setText("Welcome " + first_name);


        Intent TimetableIntent = Intents.timetableIntent(this);
        Intent LibraryIntent = Intents.libraryIntent(this);
        Intent ForumIntent = Intents.forumIntent(this);
        Intent ModulesIntent = Intents.modulesIntent(this);
        Intent AcademicProfileIntent = Intents.academicProfileIntent(this);
        Intent StudentActivitiesIntent = Intents.studentActivitiesIntent(this);


        timetable.setOnClickListener(v -> {
            startActivity(TimetableIntent);
        });

        modules.setOnClickListener(v -> {
            startActivity(ModulesIntent);
        });


        library.setOnClickListener(v -> {
            startActivity(LibraryIntent);
        });

        forum.setOnClickListener(v -> {
            startActivity(ForumIntent);
        });

        academicProfile.setOnClickListener(v -> {
            startActivity(AcademicProfileIntent);
        });

        studentActivities.setOnClickListener(v -> {
            startActivity(StudentActivitiesIntent);
        });


    }
}