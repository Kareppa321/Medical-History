package com.example.medicalhistory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
//    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signUp;
    private TextView signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



//        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUp = findViewById(R.id.signUpButton);
        signIn = findViewById(R.id.signInTextView);

        fAuth = FirebaseAuth.getInstance();

//        if(fAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
//            finish();
//        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

//                if(TextUtils.isEmpty(name)) {
//                    nameEditText.setError("Name is Required...");
//                    return;
//                }

                if(TextUtils.isEmpty(email)) {
                    emailEditText.setError("Email is Required...");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Password is Required...");
                    return;
                }

                if(password.length() < 7) {
                    passwordEditText.setError("Password must at least six characters");
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                        }else {
                            Toast.makeText(SignUpActivity.this, "Error! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

//        signIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivitySignIn();
//            }
//        });
    }
    public void startActivitySignIn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startDashBoardActivity() {
        if(emailEditText.getText().length() == 0 || passwordEditText.getText().length() == 0) {
            Toast.makeText(this, "Cannot leave fields empty", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, DashBoardActivity.class);
        startActivity(intent);

    }
}