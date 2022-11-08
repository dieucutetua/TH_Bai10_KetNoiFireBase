package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private EditText editEmail, editPass;
    private ImageView buttonSignup;
    private FirebaseAuth mAuth;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mAuth = FirebaseAuth.getInstance();

        editEmail = (EditText) findViewById(R.id.tvEmail);
        editPass = (EditText) findViewById(R.id.txtPassword);
        buttonSignup = (ImageView) findViewById(R.id.btnSignup);


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });
    }



    private void SignUp() {
        String email, pass;
        email = editEmail.getText().toString();
        pass = editPass.getText().toString();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Nhập Email !",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Nhập Password !",Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Đăng kí thành công ! ",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Đăng kí thất bại !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
