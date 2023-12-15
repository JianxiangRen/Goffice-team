package com.example.officebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private EditText idEditText, passwordEditText;
    private String id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.registerRegister);
        idEditText = findViewById(R.id.registerName);
        passwordEditText = findViewById(R.id.registerPassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = idEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();

                if (validateInput(id, password)) {
                    registerUser(id, password);
                }
            }
        });
    }

    private boolean validateInput(String id, String password) {
        if (id.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void registerUser(String userID, String password) {
        OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = "{\"userID\":\"" + userID + "\", \"password\":\"" + password + "\", \"firstName\":\"\", \"lastName\":\"\", \"emailAddr\":\"\"}";
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url("http://api.goffice.fun/register")
                .post(body)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Network error: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                runOnUiThread(() -> {
                    if (response.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed: " + response.message(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
