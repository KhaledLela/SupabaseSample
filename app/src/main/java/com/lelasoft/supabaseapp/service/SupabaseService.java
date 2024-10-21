package com.lelasoft.supabaseapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class SupabaseService {
    private static final String SUPABASE_URL = "https://wxmcphmlufvpjgdeelcd.supabase.co"; // Add your URL
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind4bWNwaG1sdWZ2cGpnZGVlbGNkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjgxNTUxNDgsImV4cCI6MjA0MzczMTE0OH0.VBo2cU_PxykArMtcpSuHMny18Hyjf5RoOROia0Uid8k"; // Add your key

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(chain -> {
                        return chain.proceed(chain.request().newBuilder()
                                .addHeader("apikey", API_KEY)
                                .addHeader("Authorization", "Bearer " + API_KEY)
                                .build());
                    }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(SUPABASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
