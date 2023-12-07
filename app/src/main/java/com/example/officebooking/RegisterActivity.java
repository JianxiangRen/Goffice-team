package com.example.officebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
private Button register;
    private EditText idEditText,passwordEditText;
    private String id,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.registerRegister);
        idEditText=findViewById(R.id.registerName);
        passwordEditText=findViewById(R.id.registerPassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                判定后注册，并跳转登录页
                id=idEditText.getText().toString().trim();
                password=passwordEditText.getText().toString().trim();
//                跳转登录页
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
//                销毁注册页
                finish();
            }
        });
    }
}