package com.lelasoft.supabaseapp.service;

import com.lelasoft.supabaseapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SupabaseApi {
    @POST("/auth/v1/token?grant_type=password")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("/rest/v1/users")
    Call<List<User>> getYourData();  // Replace 'your_table' and 'YourDataModel' as needed
}
