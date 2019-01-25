package com.example.joshuamugisha.github.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.joshuamugisha.github.R;
import com.example.joshuamugisha.github.adapter.GithubAdapter;
import com.example.joshuamugisha.github.model.GithubUsers;
import com.example.joshuamugisha.github.presenter.GithubPresenter;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersView{
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.userRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GithubPresenter githubPresenter = new GithubPresenter(this);
        githubPresenter.getGithubUsers();
    }
    @Override
    public void getGithubUsers(List<GithubUsers> githubUsers) {

        // RecyclerView adapter will go here
        recyclerView.setAdapter(new GithubAdapter(this, githubUsers));

    }


}
