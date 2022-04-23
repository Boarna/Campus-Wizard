package com.example.the_campus_wizard.activities;


import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.adaptors.forumCommentAdaptor;
import com.example.the_campus_wizard.globals.app_data_stores.AppSession;
import com.example.the_campus_wizard.holderClasses.TopicComment;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ForumTopic extends AppCompatActivity {


    List<Map<Object, String>> serialization;
    String document_id;
    private TextView title;
    private TextView discussion;
    private TextView user_name;
    private TextView timestamp;
    private FirebaseFirestore db = AppSession.Session.db;
    private ArrayList<TopicComment> comments_list;
    private EditText addCommentField;
    private Button addCommentBtn;
    private RecyclerView recyclerView;
    private ImageView forumTopicBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_topic);
        addCommentField = findViewById(R.id.forumAddCommentField);
        addCommentBtn = findViewById(R.id.forumAddCommentBtn);

        title = findViewById(R.id.topicTitle);
        discussion = findViewById(R.id.topicDiscussion);
        user_name = findViewById(R.id.topicCommentUsername);
        recyclerView = findViewById(R.id.topicRecyclerView);

        comments_list = new ArrayList<>();
        forumTopicBackBtn = findViewById(R.id.forumTopicBackBtn);

        String i_title = getIntent().getStringExtra("title");
        String i_discussion = getIntent().getStringExtra("discussion");
        document_id = getIntent().getStringExtra("document_id");

        title.setText(i_title);
        discussion.setText(i_discussion);

        forumTopicBackBtn.setOnClickListener(v -> finish());

        String first_name = AppSession.Session.userData.get("first_name").toString();
        String surname = AppSession.Session.userData.get("surname").toString();
        String user = first_name + " " + surname;

        addCommentBtn.setOnClickListener(v -> {
            addComment(user);
        });

        populateComments();
    }

    //Ensures that the latest comments are shown
    private void populateComments() {

        db.getInstance().collection("forum").document(document_id).get().addOnCompleteListener(task -> {
            List<Map<Object, String>> comments;
            if (task.isSuccessful()) {

                comments = (List<Map<Object, String>>) task.getResult().get("comments");

                String user;
                String comment;
                String timestamp;

                for (int i = 0; i < comments.size(); i++) {
                    user = comments.get(i).get("user_name");
                    comment = comments.get(i).get("text");
                    timestamp = comments.get(i).get("timestamp");

                    comments_list.add(new TopicComment(user, comment, timestamp));
                }

                setAdapter();

            }
        });
    }

    private void addComment(String user) {

        String comment = addCommentField.getText().toString();
        String timestamp = java.text.DateFormat.getDateTimeInstance().format(new Date());

        if (comment.length() >= 20) {
            addCommentField.setText("");
            comments_list.add(new TopicComment(user, comment, timestamp));
            db.getInstance().collection("forum").document(document_id).update("comments", comments_list).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Your comment has been sucessfully submited", Toast.LENGTH_LONG).show();
                    setAdapter();
                    IBinder token = findViewById(android.R.id.content).getWindowToken();
                    InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(token, 0);
                } else {
                    Toast.makeText(this, "Your message was not submitted.", Toast.LENGTH_LONG);
                    comments_list.remove(comments_list.size() - 1);
                }
            });
        } else {
            Toast.makeText(this, "Minimum characters are 20", Toast.LENGTH_LONG).show();
        }
    }

    //Sets the adapter and recyclerView to display the queried list.
    private void setAdapter() {
        forumCommentAdaptor adapter = new forumCommentAdaptor(new ArrayList(comments_list));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}
