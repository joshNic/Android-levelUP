package com.example.joshuamugisha.github.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {

    private GithubService() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BASE_URL = "https://api.github.com/";
    private Retrofit retrofit = null;

   public Retrofit getDeveloperData() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
