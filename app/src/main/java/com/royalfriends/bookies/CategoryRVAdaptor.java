package com.royalfriends.bookies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;

public class CategoryRVAdaptor extends RecyclerView.Adapter<CategoryRVAdaptor.ViewHolder> {

    private ArrayList<CategoryRVModal> categoryRVModals;
    private Context context;
    private CategorClickInterface categorClickInterface;

    public CategoryRVAdaptor(ArrayList<CategoryRVModal> categoryRVModals, Context context, CategorClickInterface categorClickInterface) {
        this.categoryRVModals = categoryRVModals;
        this.context = context;
        this.categorClickInterface = categorClickInterface;
    }

    @NonNull
    @Override
    public CategoryRVAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item,parent,false);
        return new CategoryRVAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdaptor.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryRVModal categoryRVModal =  categoryRVModals.get(position);
        holder.categoryTV.setText(categoryRVModal.getCategory());
        Picasso.get().load(categoryRVModal.getCategoryImageUrl()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categorClickInterface.onCategoryClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryRVModals.size();
    }

    public interface CategorClickInterface{
        void onCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView categoryTV;
        private ImageView categoryIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV = itemView.findViewById(R.id.idTVCategories);
            categoryIV = itemView.findViewById(R.id.idIVcategory);


        }
    }
}

