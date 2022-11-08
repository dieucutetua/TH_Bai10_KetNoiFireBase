package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail, editPass;
    private Button buttonLogin, buttonSignup;
    private FirebaseAuth mAuth;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();

        editEmail = (EditText) findViewById(R.id.edtEmail);
        editPass = (EditText) findViewById(R.id.edtPassword);
        buttonLogin = (Button) findViewById(R.id.btnDangnhap);
        buttonSignup = (Button) findViewById(R.id.btnDangki);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });
    }

    private void SignUp() {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
    }

    private void Login() {
        String email, pass;
        email = editEmail.getText().toString();
        pass = editPass.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Nhap Email !",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Nhap Password !",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Dang nhap thanh cong !", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Dang nhap khong thanh cong !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
