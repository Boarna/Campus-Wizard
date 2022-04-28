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

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_campus_wizard.R;
import com.example.the_campus_wizard.globals.app_data_stores.AppSession;
import com.example.the_campus_wizard.globals.general_functions.GeneralFunctions;
import com.example.the_campus_wizard.globals.general_functions.Intents;
import com.example.the_campus_wizard.holderClasses.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Register extends AppCompatActivity {

    private EditText v_user_id;
    private EditText v_first_name;
    private EditText v_surname;
    private EditText v_email;
    private EditText v_password;
    private EditText v_retype_password;

    private Button registerBtn;
    private ImageView registerBackBtn;
    private TextView textView;

    private FirebaseAuth mAuth = AppSession.Session.mAuth;
    private FirebaseFirestore db = AppSession.Session.db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = findViewById(R.id.registerBtn);
        registerBackBtn = findViewById(R.id.registerBackBtn);
        v_user_id = findViewById(R.id.registerID);
        v_first_name = findViewById(R.id.first_name);
        v_surname = findViewById(R.id.surname);
        v_email = findViewById(R.id.register_email);
        v_password = findViewById(R.id.register_password);
        v_retype_password = findViewById(R.id.retype_password);

        registerBackBtn.setOnClickListener(v -> finish());

        registerBtn.setOnClickListener(view -> {

            String user_id = v_user_id.getText().toString().trim();
            String first_name = v_first_name.getText().toString().trim();
            String surname = v_surname.getText().toString().trim();
            String email = v_email.getText().toString().trim();
            String password = v_password.getText().toString().trim();
            String retype_password = v_retype_password.getText().toString().trim();

            if (!user_id.equals("") && !first_name.equals("") && first_name.length() >= 2 && surname.length() >= 2 && password.length() >= 6 && password.equals(retype_password) && user_id.matches("[0-9]+") && user_id.length() == 10 && GeneralFunctions.isValidEmail(email)) {
                proccessRegister(user_id, first_name, surname, email, password);
            } else {
                if (first_name.length() < 2 || surname.length() < 2) {
                    Toast.makeText(this, "The first and last names should be at least 2 characters", Toast.LENGTH_SHORT).show();
                }
                if (user_id.length() < 10) {
                    Toast.makeText(this, "The student id should be 10 characters long and contain numbers only", Toast.LENGTH_LONG).show();
                }
                if (!GeneralFunctions.isValidEmail(email)) {
                    Toast.makeText(this, "Please input a valid email address", Toast.LENGTH_LONG).show();
                }

            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void proccessRegister(String user_id, String first_name, String surname, String email, String password) {

        Student student = new Student(user_id, first_name, surname, email);
        DocumentReference docIdRef = db.getInstance().collection("student").document(user_id);

        docIdRef.get().addOnCompleteListener(firestore_task -> {
            if (firestore_task.isSuccessful()) {
                DocumentSnapshot document = firestore_task.getResult();
                if (!document.exists()) {
                    mAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            docIdRef.set(student);
                            IBinder token = findViewById(android.R.id.content).getWindowToken();
                            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            mgr.hideSoftInputFromWindow(token, 0);
                            startActivity(Intents.registerSuccIntent(this));

                        } else {
                            Toast.makeText(getApplicationContext(), "Please try again!", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "User already exists!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}