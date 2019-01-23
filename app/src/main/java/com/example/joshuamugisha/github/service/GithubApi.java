package com.example.joshuamugisha.github.service;

import com.example.joshuamugisha.github.model.GithubUsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubApi {

    @GET("search/users?q=location:nairobi+language:java")
    Call<List<GithubUsersResponse>> developerData();
}
