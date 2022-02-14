package com.example.finalprojectbcs430W;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BuildAccountUser extends AppCompatActivity {

    EditText firstName, lastName, weight, feet, inches, gender, phoneNum, gymLoc, faceUser;
    String fN, lN, w, ft, in, g, pN, wP, gL, fU, dP;
    Button finishReg;
    FirebaseFirestore db;
    public static final String TAG = "YOUR-TAG-NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_build_user);

        //Pulling intent information from previous activity screen
        Intent intent = getIntent();

        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        weight = findViewById(R.id.editTextUserWeight);
        feet = findViewById(R.id.editTextUserHeightFt);
        inches = findViewById(R.id.editTextUserHeightIn);
        gender = findViewById(R.id.editTextUserGender);
        phoneNum = findViewById(R.id.editTextPhoneNum);
        gymLoc = findViewById(R.id.editTextGymLocation);
        faceUser = findViewById(R.id.editTextFaceUser);
        finishReg = findViewById(R.id.buttonFinishRegis);

        //Creating a dropdown menu for Preferred Workout Times
        Spinner workoutSpin = findViewById(R.id.PrefWorkoutSpin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.preferredTimes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workoutSpin.setAdapter(adapter);

        //Creating a dropdown menu for Diet Preference
        Spinner dietTypeSpin = findViewById(R.id.dietPrefSpin);
        ArrayAdapter<CharSequence> adapter_diet = ArrayAdapter.createFromResource(this,
                R.array.dietPreferences, android.R.layout.simple_spinner_item);
        adapter_diet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dietTypeSpin.setAdapter(adapter_diet);

        db = FirebaseFirestore.getInstance();

        Toast invalidName = Toast.makeText(this, "First and Last Name required.", Toast.LENGTH_LONG);

        finishReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firstName.equals("") || lastName.equals("")) {
                    invalidName.show();
                } else {
                    fN = firstName.getText().toString();
                    lN = lastName.getText().toString();
                    w = weight.getText().toString();
                    ft = feet.getText().toString();
                    in = inches.getText().toString();
                    g = gender.getText().toString();
                    pN = phoneNum.getText().toString();
                    wP = workoutSpin.getSelectedItem().toString();
                    dP = dietTypeSpin.getSelectedItem().toString();
                    gL = gymLoc.getText().toString();
                    fU = faceUser.getText().toString();
                    if (w == "") {
                        w = "Prefer Not to Say";
                    }
                    if (ft == "") {
                        ft = "Prefer Not to Say";
                    }
                    if (in == "") {
                        in = "Prefer Not to Say";
                    }
                    if (g == "") {
                        g = "Prefer Not to Say";
                    }
                    if (pN == "") {
                        pN = "Prefer Not to Say";
                    }
                    if (wP == "") {
                        wP = "Prefer Not to Say";
                    }
                    if (dP == "") {
                        dP = "Prefer Not to Say";
                    }
                    if (gL == "") {
                        gL = "Prefer Not to Say";
                    }
                    if (fU == "") {
                        fU = "Prefer Not to Say";
                    }

                    Map<String, Object> user = new HashMap<>();
                    user.put("First Name", fN);
                    user.put("Last Name", lN);
                    user.put("Email", intent.getStringExtra("EMAIL"));
                    user.put("Weight", w);
                    user.put("Height (Feet)", ft);
                    user.put("Height (Inches)", in);
                    user.put("Gender", g);
                    user.put("Phone Number", pN);
                    user.put("Preferred Workout Time", wP);
                    user.put("Diet Preference", dP);
                    user.put("Gym Location", gL);
                    user.put("Facebook Username", fU);

                    db.collection("user")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());

                                    Intent myIntent = new Intent(BuildAccountUser.this, MainHomePage.class);
                                    BuildAccountUser.this.startActivity(myIntent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }
            }
        });
    }
}