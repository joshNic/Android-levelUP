package com.example.joshuamugisha.github.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.joshuamugisha.github.R;
import com.example.joshuamugisha.github.adapter.GithubAdapter;
import com.example.joshuamugisha.github.model.GithubUsers;
import com.example.joshuamugisha.github.presenter.GithubPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersView{
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.userRecyclerView);

        GithubPresenter githubPresenter = new GithubPresenter(this);
        githubPresenter.getGithubUsers();

        mLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);


    }
    @Override
    public void getGithubUsers(List<GithubUsers>githubUsers) {
        recyclerView.setAdapter(new GithubAdapter(this, githubUsers));
    }

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        listState = mLayoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, listState);
    }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        if(state != null)
            listState = state.getParcelable(LIST_STATE_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            mLayoutManager.onRestoreInstanceState(listState);
        }
    }


}
