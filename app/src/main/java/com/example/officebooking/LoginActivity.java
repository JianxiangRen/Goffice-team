package com.example.officebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
private Button login;
private EditText idEditText,passwordEditText;
private String id,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.loginLogin);
        idEditText=findViewById(R.id.loginName);
        passwordEditText=findViewById(R.id.loginPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                判定后登录
                id=idEditText.getText().toString().trim();
                password=passwordEditText.getText().toString().trim();
//          登录
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}