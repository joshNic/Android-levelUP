package com.example.joshuamugisha.github.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {

//    public GithubService() {
//        throw new IllegalStateException("Utility class");
//    }

    public static final String BASE_URL = "https://api.github.com";
    private static  Retrofit retrofit = null;

   public static Retrofit getDeveloperData() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
