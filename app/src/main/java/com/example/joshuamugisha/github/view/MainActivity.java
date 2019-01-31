package com.example.joshuamugisha.github.view;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import android.widget.ProgressBar;
import android.widget.Toast;


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
    private SwipeRefreshLayout swipeContainer;
    private ProgressBar progressBar;
    private GridLayoutManager mLayoutManager;
    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.userRecyclerView);
        swipeContainer = findViewById(R.id.swipeContainer);
        progressBar = findViewById(R.id.indeterminateBar);
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


        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager = new GridLayoutManager(this, 2);
        } else {
            mLayoutManager = new GridLayoutManager(this, 3);
        }


        recyclerView.setLayoutManager(mLayoutManager);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                githubPresenter.getGithubUsers();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = new GridLayoutManager(this, 3);
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager = new GridLayoutManager(this, 2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the actionbar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                swipeContainer.setRefreshing(true);
                githubPresenter.getGithubUsers();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void getGithubUsers(List<GithubUsers>githubUsers) {
        recyclerView.setAdapter(new GithubAdapter(this, githubUsers));
        swipeContainer.setRefreshing(false);
        progressBar.setVisibility(View.GONE);

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