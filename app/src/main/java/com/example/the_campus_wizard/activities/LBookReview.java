package com.example.the_campus_wizard.activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.adaptors.lreview_adaptor;
import com.example.the_campus_wizard.globals.app_data_stores.AppSession;
import com.example.the_campus_wizard.holderClasses.Review;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LBookReview extends AppCompatActivity {

    ArrayList<Review> reviews_list;
    private FirebaseFirestore db = AppSession.Session.db;
    private RecyclerView recyclerView;
    private TextView title;
    private TextView author;
    private TextView edition;
    private Button reviewBtn;
    private Button reserveBtn;
    private TextView addReviewText;
    private String document_id;
    private ImageView bookIMG;
    private ImageView lBookBackBtn;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lbook_review);

        recyclerView = findViewById(R.id.lRevRecyclerView);
        reviewBtn = findViewById(R.id.lReviewBtn);
        reserveBtn = findViewById(R.id.lBookReserveBtn);

        addReviewText = findViewById(R.id.lReviewField);

        title = findViewById(R.id.lBookTitle);
        author = findViewById(R.id.lBookAuthor);
        edition = findViewById(R.id.lBookEdition);
        bookIMG = findViewById(R.id.lBookPageImg);

        title.setText(getIntent().getStringExtra("title"));
        author.setText(getIntent().getStringExtra("author"));
        edition.setText(getIntent().getStringExtra("edition"));
        reviews_list = new ArrayList<>();
        bookIMG.setImageBitmap(getIntent().getParcelableExtra("img"));
        document_id = getIntent().getStringExtra("document_id");
        lBookBackBtn = findViewById(R.id.lBookBackBtn);

        lBookBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        document_id = getIntent().getStringExtra("document_id");

        String first_name = "";
        String surname = "";
        String user = "";
        try {
            first_name = AppSession.Session.userData.get("first_name").toString();
            surname = AppSession.Session.userData.get("surname").toString();

        }catch (Exception e){

        }

        user = first_name + " " + surname;

        //Checking for reserved status and creates initial comment list. This ensures comments shown are syncronised with the database.
        db.getInstance().collection("library_book").document(document_id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                String isReserved = task.getResult().get("isReserved").toString();

                if (isReserved.equals("true")) {
                    reserveBtn.setText("Cancel reservation");
                } else {
                    reserveBtn.setText("Reserve this book");
                }

                List<Map<Object, String>> reviews = (List<Map<Object, String>>) task.getResult().get("Reviews");

                for (int i = 0; i < reviews.size(); i++) {

                    String name = reviews.get(i).get("name");
                    String comment = reviews.get(i).get("comment");
                    reviews_list.add(new Review(name, comment));

                    setAdapter();
                }
            }
        });

        String finalUser = user;
        reviewBtn.setOnClickListener(v -> {

            addReview(finalUser);
        });

        reserveBtn.setOnClickListener(v -> {
            reserveBook(finalUser);
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addReview(String user) {

        String comment = addReviewText.getText().toString();

        if ((comment.length() > 20)) {
            reviews_list.add(new Review(user, comment));

            db.getInstance().collection("library_book").document(document_id).update("Reviews", reviews_list).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    setAdapter();
                    addReviewText.setText("");
                    Toast.makeText(this, "Your review has been sucessfully submited", Toast.LENGTH_LONG).show();
                    IBinder token = findViewById(android.R.id.content).getWindowToken();
                    InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(token, 0);
                } else {
                    reviews_list.remove(reviews_list.size() - 1);
                }
            });
        } else {
            Toast.makeText(this, "Minimum review size is 50 characters", Toast.LENGTH_LONG).show();
        }

    }

    private void reserveBook(String user) {
        db.getInstance().collection("library_book").document(document_id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String isReserved = task.getResult().get("isReserved").toString();
                if (isReserved.equals("false")) {
                    isReserved = "true";
                } else if (isReserved.equals("true")) {
                    isReserved = "false";
                }

                String finalIsReserved = isReserved;
                db.getInstance().collection("library_book").document(document_id).update("isReserved", isReserved, "reservedName", user).addOnCompleteListener(set_task -> {
                    if (set_task.isSuccessful()) {

                        if (finalIsReserved.equals("true")) {
                            reserveBtn.setText("Cancel reservation");
                        } else {
                            reserveBtn.setText("Reserve this book");
                        }

                    } else {
                        Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_LONG).show();
            }

        });
    }

    //Sets the adapter and recyclerView to display the queried list.
    private void setAdapter() {
        lreview_adaptor adapter = new lreview_adaptor(new ArrayList(reviews_list));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        System.out.println(adapter.getItemCount());
    }

}
