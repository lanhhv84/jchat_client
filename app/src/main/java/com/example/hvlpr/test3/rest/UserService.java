package com.example.hvlpr.test3.rest;

import com.example.hvlpr.test3.RestModel.BooleanResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @GET("/user/login")
    Call<BooleanResponse> login(@Query("username") String username, @Query("password") String password);

    @GET("/user/add")
    Call<BooleanResponse> add(@Query("username") String username,
                              @Query("password") String password,
                              @Query("nickname") String nickname);
}
