package com.example.joshuamugisha.github.service;

import com.example.joshuamugisha.github.model.GithubUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubApi {

    @GET("/search/users?q=location:nairobi+language:java")
    Call<GithubUsersResponse> developerData();
}
