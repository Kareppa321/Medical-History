package com.example.medicalhistory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    private EditText loginEmail;
    private EditText loginPassword;
    private Button logIn;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEmail = findViewById(R.id.emailEditText);
        loginPassword = findViewById(R.id.passwordEditText);
        logIn = findViewById(R.id.signInButton);
        signUp = findViewById(R.id.signUpTextView);

        fAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    loginEmail.setError("Email is Required...");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    loginEmail.setError("Password is Required...");
                    return;
                }

                if(password.length() < 6) {
                    loginPassword.setError("Password must at least six characters");
                }

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            System.out.println(task);
                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                            startActivity(new Intent(MainActivity.this, DashBoardActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Error! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

//        signUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

//        signUp.setOnClickListener(new View.OnClickListener(){
//            @override
//                    public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
//            }
//        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

    }

//    signUp.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
//        }
//    });




    public void startSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void startDashBoardActivity() {
        if(loginEmail.getText().length() == 0 || loginPassword.getText().length() == 0) {
            Toast.makeText(this, "Cannot leave fields empty", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, DashBoardActivity.class);
        startActivity(intent);
    }
}