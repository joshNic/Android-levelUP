package com.example.joshuamugisha.github.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.joshuamugisha.github.R;
import com.example.joshuamugisha.github.model.GithubUsers;
import com.example.joshuamugisha.github.view.UserDetailActivity;
import com.google.gson.Gson;

import java.util.List;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GithubUserViewHolder> {

    private Context mContext;
    private List<GithubUsers> mUserList;
    private Gson gson;


    public  GithubAdapter(Context mContext,  List<GithubUsers> mUserList) {
        this.mUserList = mUserList;
        this.mContext = mContext;

    }

    @Override
    public GithubUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_card, parent, false);

        return new GithubUserViewHolder(view);
    }


    @Override
    public void onBindViewHolder(GithubUserViewHolder holder, int position) {
        GithubUsers githubUser = mUserList.get(position);
        holder.name.setText(githubUser.getLogin());
        Glide.with(mContext)
                .load(githubUser.getAvatarUrl())
                .apply(new RequestOptions()
                .placeholder(R.drawable.ic_man))
                .into(holder.profile);
    }
    @Override
    public int getItemCount() {
        return mUserList.size();
    }
    public class GithubUserViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public CardView mUserCard;
        public ImageView profile;

        /**
         @param view
         */
        public GithubUserViewHolder(final View view) {

            super(view);
            name = view.findViewById(R.id.textView);
            mUserCard = view.findViewById(R.id.card_view);
            profile = view.findViewById(R.id.imageView);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        GithubUsers clickedUserItem =  mUserList.get(pos);
                        Intent intent = new Intent(mContext, UserDetailActivity.class);
                        intent.putExtra("GithubUser", clickedUserItem);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                }
            });

        }
    }
}
