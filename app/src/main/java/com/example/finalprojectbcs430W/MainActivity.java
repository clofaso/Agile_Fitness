package com.example.finalprojectbcs430W;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    Button login, create_account;
    FirebaseAuth mAuth;
    private FirebaseFirestore db;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // start code for animated gradient background, code inspired from https://www.youtube.com/watch?v=cDnrg75lu3E and https://www.tutorialspoint.com/how-to-create-animated-gradient-background-in-android#:~:text=This%20example%20demonstrate%20about%20how%20to%20create%20Animated,2%20%E2%88%92%20Add%20the%20following%20code%20to%20res%2Flayout%2Factivity_main.xml.
        //https://www.youtube.com/watch?v=x_DXXGvyfh8&t=216s
        ConstraintLayout constraintLayout = findViewById(R.id.main_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        // end code for animated gradient background

        //start code for the UI gradient action bar , code inspired from https://www.youtube.com/watch?v=9FgE53ur71M//
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_background));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //end code for the UI gradient action bar//

        login = findViewById(R.id.buttonLogin);
        create_account = findViewById(R.id.buttonCreateAnAccount);
        user = findViewById(R.id.editTextUsername);
        pass = findViewById(R.id.editTextPassword);
        mAuth = FirebaseAuth.getInstance();

        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        Toast invalidEntry = Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user.getText().toString();
                String password = pass.getText().toString();

                if (email.trim().equals(null) || email.trim().equals("") || password.trim().equals(null) || password.trim().equals("")) {
                    invalidEntry.show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // Was the sign in successful?
                                    if (task.isSuccessful()) {
                                        Intent myIntent = new Intent(MainActivity.this, MainHomePage.class);
                                        MainActivity.this.startActivity(myIntent);
                                        finish();
                                        Toast.makeText(getApplicationContext(), getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent create = new Intent(MainActivity.this, CreateAccount.class);
                MainActivity.this.startActivity(create);

                }
        });
    }
}