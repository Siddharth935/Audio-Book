package com.royalfriends.bookies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;

public class NewsRVAdaptor extends RecyclerView.Adapter<NewsRVAdaptor.ViewHolder> {
    private ArrayList<Articles> articlesArrayList;
    private Context context;

    public NewsRVAdaptor(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRVAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_iem,parent,false);
        return new NewsRVAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRVAdaptor.ViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.subtitleTV.setText(articles.getDescription());
        holder.titleTV.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToImage()).into((ImageView) holder.newsIV);
//        Picasso.get().load(articles.getUrlToImage()).into((Target) holder.newsIV);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,NewsDetailActivity.class);
                i.putExtra("title",articles.getTitle());
                i.putExtra("content",articles.getContent());
                i.putExtra("desc",articles.getDescription());
                i.putExtra("image",articles.getUrlToImage());
                i.putExtra("url",articles.getUrl());
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTV,subtitleTV;
        private View newsIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVNewsHeading);
            subtitleTV = itemView.findViewById(R.id.idTVSubTitle);
            newsIV = itemView.findViewById(R.id.idIVNews);
        }
    }
}
