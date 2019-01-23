package com.example.joshuamugisha.github.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUsersResponse implements Parcelable {

    public static final Parcelable.Creator<GithubUsersResponse> CREATOR = new Creator<GithubUsersResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GithubUsersResponse createFromParcel(Parcel in) {
            return new GithubUsersResponse(in);
        }

        public GithubUsersResponse[] newArray(int size) {
            return (new GithubUsersResponse[size]);
        }

    };
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<GithubUsers> githubUsers = null;

    protected GithubUsersResponse(Parcel in) {
        this.totalCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.incompleteResults = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.githubUsers, (GithubUsers.class.getClassLoader()));
    }

    public GithubUsersResponse() {
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<GithubUsers> getGithubUsers() {
        return githubUsers;
    }

    public void setGithubUsers(List<GithubUsers> githubUsers) {
        this.githubUsers = githubUsers;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totalCount);
        dest.writeValue(incompleteResults);
        dest.writeList(githubUsers);
    }

    public int describeContents() {
        return 0;
    }

}
