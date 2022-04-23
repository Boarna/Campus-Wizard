package com.example.the_campus_wizard.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.adaptors.newsletterAdaptor;
import com.example.the_campus_wizard.globals.app_data_stores.AppSession;
import com.example.the_campus_wizard.holderClasses.News;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class Activities extends AppCompatActivity {

    private FirebaseFirestore db = AppSession.Session.db;
    private RecyclerView recyclerView;
    private ArrayList<News> news_list;
    private ImageView activitiesBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        recyclerView = findViewById(R.id.newsletterRecyclerView);
        news_list = new ArrayList<>();
        activitiesBackBtn = findViewById(R.id.activitiesBackBtn);

        activitiesBackBtn.setOnClickListener(v -> {
            finish();
        });

        db.getInstance().collection("news").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot result : Objects.requireNonNull(task.getResult())) {
                    String title = Objects.requireNonNull(result.get("title")).toString();
                    String topic = Objects.requireNonNull(result.get("topic")).toString();

                    news_list.add(new News(title, topic));
                }
                setAdapter();
            }
        });
    }

    //Sets the adapter and recyclerView to display the queried list.
    private void setAdapter() {
        newsletterAdaptor adapter = new newsletterAdaptor(new ArrayList(news_list));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        System.out.println(adapter.getItemCount());
    }
}

