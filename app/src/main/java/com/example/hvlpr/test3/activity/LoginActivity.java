package com.example.hvlpr.test3.activity;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.hvlpr.test3.R;
import com.example.hvlpr.test3.RestModel.BooleanResponse;
import com.example.hvlpr.test3.rest.RestClient;
import com.example.hvlpr.test3.rest.RetrofitClientInstance;
import com.example.hvlpr.test3.rest.UserService;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        RequestParams requestParams = new RequestParams();
        requestParams.add("username", userNameEditText.getText().toString());
        requestParams.add("password", passwordEditText.getText().toString());

        UserService userService = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
        Call<BooleanResponse> repos = userService.login(userNameEditText.getText().toString(), passwordEditText.getText().toString());
        Log.d("Fuck", repos.toString());
        repos.enqueue(new Callback<BooleanResponse>() {
            @Override
            public void onResponse(Call<BooleanResponse> call, Response<BooleanResponse> response) {
                if (response.body().getValue()) {
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    i.putExtra("username", userNameEditText.getText().toString());
                    finish();
                    startActivity(i);
                }
                else {
                    Snackbar.make(view, "Login Fail", Snackbar.LENGTH_LONG)
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
    public void onSignUpButtonClicked(View view) {
        startActivity(new Intent(getBaseContext(), SignUpActivity.class));
    }
}
