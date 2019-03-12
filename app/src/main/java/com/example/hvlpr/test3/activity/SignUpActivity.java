package com.example.hvlpr.test3.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.hvlpr.test3.R;
import com.example.hvlpr.test3.RestModel.BooleanResponse;
import com.example.hvlpr.test3.rest.RetrofitClientInstance;
import com.example.hvlpr.test3.rest.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        UserService userService = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);

        Call<BooleanResponse> repos = userService.add(userNameEditText.getText().toString(), passwordEditText.getText().toString(), nickNameEditText.getText().toString());
        Log.d("Fuck", repos.toString());


        repos.enqueue(new Callback<BooleanResponse>() {
            @Override
            public void onResponse(Call<BooleanResponse> call, Response<BooleanResponse> response) {
                if (response.body().getValue()) {
                    Snackbar.make(view, "Sign up successfully", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    Snackbar.make(view, "Sign up fail", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            @Override
            public void onFailure(Call<BooleanResponse> call, Throwable t) {
                Snackbar.make(view, "Request Fail", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    public void onLoginButtonClicked(View view) {
        finish();
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
    }
}
