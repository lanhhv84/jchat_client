package com.example.hvlpr.test3.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hvlpr.test3.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText nickNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userNameEditText = findViewById(R.id.usernameEditText);
        passwordEditText =findViewById(R.id.passwordEditText);
        nickNameEditText = findViewById(R.id.nickNameEditText);
    }
    public void onSignUpButtonClicked(View view) {
        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Sign up successfully", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public void onLoginButtonClicked(View view) {
        finish();
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
    }
}
