package com.example.joshuamugisha.github.presenter;

import android.util.Log;

import com.example.joshuamugisha.github.model.GithubUsers;
import com.example.joshuamugisha.github.model.GithubUsersResponse;
import com.example.joshuamugisha.github.service.GithubApi;
import com.example.joshuamugisha.github.service.GithubService;
import com.example.joshuamugisha.github.view.UsersView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {

    private GithubService githubService;
    private UsersView usersView;
    private GithubApi apiService;

    public GithubPresenter(UsersView view) {
        this.usersView = view;

        if (this.usersView == null) {
            this.githubService = new GithubService();
        }
    }

    public void getGithubUsers() {
        apiService = githubService.getDeveloperData().create(GithubApi.class);

        Call<GithubUsersResponse> call = apiService.developerData();
        call.enqueue(new Callback<GithubUsersResponse>() {
            @Override
            public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                List<GithubUsers> result = response.body().getItems();
                usersView.getGithubUsers(result);
            }

            @Override
            public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("TAG", t.toString());
            }
        });


    }
}
