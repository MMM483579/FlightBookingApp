package com.example.kiwi30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText editTextTextPersonName3, editTextTextPersonName4;
    Button button2;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Kiwi30);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        button2 = findViewById(R.id.button2);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }


    public void registerUser(){
        String email = editTextTextPersonName3.getText().toString().trim();
        String password = editTextTextPersonName4.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            showToast("Enter email address!");
            return;
        }

        if(TextUtils.isEmpty(password)){
            showToast("Enter Password!");
            return;
        }

        if(password.length() < 6){
            showToast("Password too short, enter minimum 6 characters");
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "New user registration: " + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            SignupActivity.this.showToast("Authentication failed. " + task.getException());
                        } else {
                            SignupActivity.this.startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            SignupActivity.this.finish();
                        }
                    }

                });
    }

    public void showToast(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }

}