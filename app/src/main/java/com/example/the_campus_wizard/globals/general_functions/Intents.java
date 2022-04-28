package com.example.the_campus_wizard.globals.general_functions;


import android.content.Context;
import android.content.Intent;

import com.example.the_campus_wizard.activities.Cyber;
import com.example.the_campus_wizard.activities.Forum;
import com.example.the_campus_wizard.activities.Library;
import com.example.the_campus_wizard.activities.Login;
import com.example.the_campus_wizard.activities.Modules;
import com.example.the_campus_wizard.activities.RegisterSuccessful;
import com.example.the_campus_wizard.activities.Timetable;

public class Intents {

    public static Intent dashboardIntent(Context context) {
        return new Intent(context, com.example.the_campus_wizard.activities.StudentDashboard.class);
    }

    public static Intent registerIntent(Context context) {
        return new Intent(context, com.example.the_campus_wizard.activities.Register.class);
    }

    public static Intent registerSuccIntent(Context context) {
        return new Intent(context, RegisterSuccessful.class);
    }

    public static Intent loginIntent(Context context) {
        return new Intent(context, Login.class);
    }

    public static Intent libraryIntent(Context context) {
        return new Intent(context, Library.class);
    }

    public static Intent timetableIntent(Context context) {
        return new Intent(context, Timetable.class);
    }

    public static Intent modulesIntent(Context context) {
        return new Intent(context, Modules.class);
    }

    public static Intent CyberIntent(Context context) {
        return new Intent(context, Cyber.class);
    }

    public static Intent forumIntent(Context context) {
        return new Intent(context, Forum.class);
    }


    public static Intent lBookRIntent(Context context) {
        return new Intent(context, com.example.the_campus_wizard.activities.LBookReview.class);
    }


    public static Intent forgotPasswordIntent(Context context) {
        return new Intent(context, com.example.the_campus_wizard.activities.PasswordReset.class);

    }

    public static Intent academicProfileIntent(Context context) {
        return new Intent(context, com.example.the_campus_wizard.activities.AcademicProfile.class);

    }

    public static Intent studentActivitiesIntent(Context context) {
        return new Intent(context, com.example.the_campus_wizard.activities.StudentActivities.class);
    }


    public static Intent forumDiscussionSubjectsIntent(Context context) {
        return new Intent(context, com.example.the_campus_wizard.activities.ForumDiscussionSubjects.class);
    }

    public static Intent forumTopicIntent(Context context) {
        return new Intent(context, com.example.the_campus_wizard.activities.ForumTopic.class);
    }


}
