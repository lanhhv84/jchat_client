package com.example.hvlpr.test3.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hvlpr.test3.R;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    public void onLoginButtonClicked(View view) {

        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("abc", "abc");
        loginInfo.put("abcdef", "abcdef");


        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        String pass = loginInfo.get(userName);
        if (pass != null && pass.equals(password)) {

            Intent i = new Intent(getBaseContext(), MainActivity.class);
            i.putExtra("PersonID", userName);
            finish();
            startActivity(i);
        }
        else {
            Snackbar.make(view, "Fail", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public void onSignUpButtonClicked(View view) {
        startActivity(new Intent(getBaseContext(), SignUpActivity.class));
    }
}
