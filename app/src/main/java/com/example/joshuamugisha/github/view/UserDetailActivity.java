package com.example.joshuamugisha.github.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.joshuamugisha.github.R;
import com.example.joshuamugisha.github.model.GithubUsers;

public class UserDetailActivity extends AppCompatActivity {
    public GithubUsers mGithubUserDetails;
    private Toolbar mToolbar;
    private ImageView mProfileImage, mShareLink, mVistRepo;
    private AppBarLayout mAppBarLayout;
    private TextView mGithubUserRepository, mGithubUserShareLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details);
        mToolbar = findViewById(R.id.toolbar);
        mProfileImage = findViewById(R.id.profileImage);
        mShareLink = findViewById(R.id.vistRepoLinkImageView);
        mVistRepo = findViewById(R.id.shareImageView);
        mGithubUserRepository = findViewById(R.id.userRepoLink);
        mGithubUserShareLink = findViewById(R.id.userNameTextView);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("GithubUser")) {
            mGithubUserDetails = getIntent().getParcelableExtra("GithubUser");
            initCollapsingToolbar(mGithubUserDetails.getLogin());
            mGithubUserShareLink.setText(mGithubUserDetails.getLogin());
            mGithubUserRepository.setText(mGithubUserDetails.getHtmlUrl());
            Glide.with(this)
                    .load(mGithubUserDetails.getAvatarUrl())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_man))
                    .into(mProfileImage);


            mShareLink.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            String shareBody = mGithubUserDetails.getHtmlUrl();
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Checkout this java Developer on Github" + " " + mGithubUserDetails.getLogin());
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                            startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        }
                    }
            );

            mVistRepo.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent webBrowserIntent = new Intent(Intent.ACTION_VIEW);
                            webBrowserIntent.setData(Uri.parse(mGithubUserDetails.getHtmlUrl()));
                            startActivity(webBrowserIntent);
                        }
                    }
            );


        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar(final String name) {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mGithubUserDetails.getLogin());
        mAppBarLayout = findViewById(R.id.appbar);
        mAppBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(name);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
