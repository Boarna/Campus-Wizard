package com.example.the_campus_wizard.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.adaptors.libraryAdaptor;
import com.example.the_campus_wizard.globals.app_data_stores.AppSession;
import com.example.the_campus_wizard.holderClasses.LibraryBook;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Library extends AppCompatActivity {

    private SearchView librarySearchBar;
    private RadioButton computing;
    private RadioButton economics;
    private RadioButton business;
    private FirebaseFirestore db = AppSession.Session.db;
    private RecyclerView recyclerView;
    private ArrayList<LibraryBook> books_list;
    private StorageReference storageRef;
    private ImageView libraryLBackBtn;
    private RadioGroup libraryRadioGroup;
    private String radio_category = "Computing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        recyclerView = findViewById(R.id.booksRecyclerView);
        books_list = new ArrayList<>();

        computing = findViewById(R.id.computingR);
        economics = findViewById(R.id.economicsR);
        business = findViewById(R.id.businessR);

        librarySearchBar = findViewById(R.id.librarySearchBar);

        libraryLBackBtn = findViewById(R.id.libraryLBackBtn);

        libraryLBackBtn.setOnClickListener(v -> {
            finish();
        });

        computing.setOnClickListener(v -> {
            radio_category = "Computing";
        });

        business.setOnClickListener(v -> {
            radio_category = "Business";
        });

        economics.setOnClickListener(v -> {
            radio_category = "Economics";
        });


        //Populates the recyclerview.

        onCreateList();

        librarySearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String search = librarySearchBar.getQuery().toString();
                processListWithFilter(search);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        libraryRadioGroup = findViewById(R.id.libraryRadioGroup);

        libraryRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String search = librarySearchBar.getQuery().toString();
            processListWithFilter(search);
        });


    }

    private void processListWithFilter(String search) {

        books_list.clear();
         radio_category = "Computing";
        //Radio buttons checking
        if (computing.isChecked()) {
            radio_category = "Computing";
        } else if (economics.isChecked()) {
            radio_category = "Economics";
        } else if (business.isChecked()) {
            radio_category = "Business";
        }else{
            radio_category = "not_applicable";
        }

        String finalRadio_category = radio_category;

        if (finalRadio_category != "not_applicable") {

            db.getInstance().collection("library_book").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    String title;
                    String author;
                    String edition;
                    String book_category;
                    String document_id;
                    String image;
                    List<Map<Object, String>> reviews;


                    for (DocumentSnapshot result :
                            Objects.requireNonNull(task.getResult()).getDocuments()
                    ) {

                        String first_name;
                        String surname;
                        String user_name = "";

                        try {
                            first_name = Objects.requireNonNull(AppSession.Session.userData.get("first_name")).toString();
                            surname = Objects.requireNonNull(AppSession.Session.userData.get("surname")).toString();
                            user_name = first_name + " " + surname;
                        } catch (Exception e) {

                        }


                        try {
                            //Attempts retrieve books reserved by the user or unreserved books. If user is not the person that reserved the book it will not be shown in the list.
                            if (Objects.requireNonNull(result.get("reservedName")).toString().equals(user_name) || Objects.requireNonNull(result.get("isReserved")).toString().equals("false")) {

                                title = Objects.requireNonNull(result.get("Title")).toString();
                                author = Objects.requireNonNull(result.get("Author")).toString();
                                edition = Objects.requireNonNull(result.get("Edition")).toString();
                                book_category = Objects.requireNonNull(result.get("Category")).toString();
                                document_id = result.getId();
                                image = Objects.requireNonNull(result.get("Image")).toString();

                                Log.d("taggggg", image.toString());

                                //Creating a temporary file to store firebase storage image for immediate use in the adaptors and to be passed to the review Activity.
                                try {

                                    File file = File.createTempFile(image, "jpg");

                                    try {
                                        storageRef = FirebaseStorage.getInstance().getReference().child("books/" + image + ".jpg");
                                    } catch (Exception e) {

                                    }

                                    String finalAuthor = author;
                                    String finalTitle = title;
                                    String finalEdition = edition;
                                    String finalBookCategory = book_category;
                                    String finalDocument_id = document_id;

                                    Log.d("Tsagdsgfsdf", "here");

                                    storageRef.getFile(file).addOnSuccessListener(taskSnapshot -> {
                                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                                        if (finalBookCategory.equals(finalRadio_category)) {
                                            if (finalTitle.contains(search) || search.equals("") || finalAuthor.contains(search) || finalEdition.contains(search)) {
                                                books_list.add(new LibraryBook(finalAuthor, finalTitle, finalEdition, finalBookCategory, bitmap, finalDocument_id));
                                            }

                                        }

                                        setAdapter();
                                        //Passes books_list and populates the recyclerView, not the most efficient way of doing it but due to the nature of asynchronous code and the need to be able to
                                        //sort based on different criteria I was not able to come with a better solution than to call this everytime the books_list updates. Please note it might become
                                        //resource intensive on bigger lists.


                                    });

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {

                        }
                    }

                }

            });

        }

    }

    private void onCreateList() {

        db.getInstance().collection("library_book").get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {

                String title;
                String author;
                String edition;
                String book_category;
                String document_id;
                String image;


                for (DocumentSnapshot result :
                        Objects.requireNonNull(task.getResult()).getDocuments()
                ) {

                    String first_name;
                    String surname;
                    String user_name = "";

                    try {
                        first_name = Objects.requireNonNull(AppSession.Session.userData.get("first_name")).toString();
                        surname = Objects.requireNonNull(AppSession.Session.userData.get("surname")).toString();
                        user_name = first_name + " " + surname;
                    } catch (Exception e) {

                    }

                    try {
                        //Attempts retrieve books reserved by the user or unreserved books. If user is not the person that reserved the book it will not be shown in the list.
                        if (Objects.requireNonNull(result.get("reservedName")).toString().equals(user_name) || Objects.requireNonNull(result.get("isReserved")).toString().equals("false")) {


                            title = Objects.requireNonNull(result.get("Title")).toString();
                            author = Objects.requireNonNull(result.get("Author")).toString();
                            edition = Objects.requireNonNull(result.get("Edition")).toString();
                            book_category = Objects.requireNonNull(result.get("Category")).toString();
                            image = Objects.requireNonNull(result.get("Image")).toString();
                            document_id = result.getId();


                            //Creating a temporary file to store firebase storage image for immediate use in the adaptors and to be passed to the review Activity.
                            try {

                                File file = File.createTempFile(image, "jpg");
                                storageRef = FirebaseStorage.getInstance().getReference().child("books/" + image + ".jpg");

                                String finalAuthor = author;
                                String finalTitle = title;
                                String finalEdition = edition;
                                String finalBookCategory = book_category;

                                String finalDocument_id = document_id;

                                storageRef.getFile(file).addOnSuccessListener(taskSnapshot -> {
                                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                                    books_list.add(new LibraryBook(finalAuthor, finalTitle, finalEdition, finalBookCategory, bitmap, finalDocument_id));

                                    //Passes books_list and populates the recyclerView, not the most efficient way of doing it but due to the nature of asynchronous code and the need to be able to
                                    //sort based on different criteria I was not able to come with a better solution than to call this everytime the books_list updates. Please note it might become
                                    //resource intensive on bigger lists.
                                    setAdapter();

                                });

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {

                    }
                }

            }
        });

    }

    //Sets the adapter and recyclerView
    private void setAdapter() {
        libraryAdaptor adapter = new libraryAdaptor(new ArrayList(books_list));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}