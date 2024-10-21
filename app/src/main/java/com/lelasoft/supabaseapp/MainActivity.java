package com.lelasoft.supabaseapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lelasoft.supabaseapp.model.User;
import com.lelasoft.supabaseapp.service.LoginRequest;
import com.lelasoft.supabaseapp.service.LoginResponse;
import com.lelasoft.supabaseapp.service.SupabaseApi;
import com.lelasoft.supabaseapp.service.SupabaseService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SupabaseApi supabaseApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable Edge-to-Edge layout
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); // Ensure the correct layout is used

        // Uncomment insets logic if needed, but make sure it doesn't interfere with button placement
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Retrofit and Supabase API
        supabaseApi = SupabaseService.getRetrofitInstance().create(SupabaseApi.class);

//        // Find the login button by its ID
//        Button loginBtn = findViewById(R.id.button);
//
//        // Set the OnClickListener for the button
//        loginBtn.setOnClickListener(view -> {
//            // Show a debug Toast message to confirm the click is working
//            Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
//
//            // Call login method (make sure it's not blocking the UI)
//            loginUser("kl@k3.io", "password123");
//        });

        loginUser("kl@k3.io", "password123");
        getUsers();
    }

    private void loginUser(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);
        Call<LoginResponse> call = supabaseApi.login(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    String accessToken = response.body().getAccess_token();
                    Toast.makeText(MainActivity.this, "Logged in!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getUsers() {
        Call<List<User>> call = supabaseApi.getYourData();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.body().toString());
                    Toast.makeText(MainActivity.this, "Logged in!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}