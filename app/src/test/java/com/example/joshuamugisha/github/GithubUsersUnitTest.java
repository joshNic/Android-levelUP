package com.example.joshuamugisha.github;

import com.example.joshuamugisha.github.model.GithubUsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GithubUsersUnitTest {
    @Test
    public void testGithubUsers() {
        String mUserName = "joshNic";
        String mGitRepo = "https://github.com/joshNic";
        String mProfileImageUrl = "https://avatars3.githubusercontent.com/u/25085146?v=4";
        GithubUsers mUserDetails = new GithubUsers(mUserName, mGitRepo, mProfileImageUrl);

        assertEquals(mUserName, mUserDetails.getLogin());
        assertEquals(mGitRepo, mUserDetails.getHtmlUrl());
        assertEquals(mProfileImageUrl, mUserDetails.getAvatarUrl());


    }
}
