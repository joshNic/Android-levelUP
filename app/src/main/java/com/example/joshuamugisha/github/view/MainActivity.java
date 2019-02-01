package com.example.joshuamugisha.github.view;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.joshuamugisha.github.Application.MyApplication;
import com.example.joshuamugisha.github.R;
import com.example.joshuamugisha.github.adapter.GithubAdapter;
import com.example.joshuamugisha.github.model.GithubUsers;
import com.example.joshuamugisha.github.presenter.GithubPresenter;
import com.example.joshuamugisha.github.util.NetworkReceiver;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersView, NetworkReceiver.NetworkReceiverListener{
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private GithubPresenter githubPresenter;
    private LinearLayoutManager mLayoutManager;
    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.userRecyclerView);
        floatingActionButton = findViewById(R.id.fab);

        checkConnection();

        githubPresenter = new GithubPresenter(this);
        githubPresenter.getGithubUsers();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
                githubPresenter.getGithubUsers();

            }
        });

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
        MyApplication.getInstance().setConnectivityListener(this);
        if (listState != null) {
            mLayoutManager.onRestoreInstanceState(listState);
        }
    }
    private void checkConnection() {
        boolean isConnected = NetworkReceiver.isConnected();
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            floatingActionButton.hide();
        } else {

            message = "Sorry! Not connected to internet";
            color = Color.WHITE;
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();
        }

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }


}