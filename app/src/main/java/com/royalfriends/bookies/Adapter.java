package com.royalfriends.bookies;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ItemClickListerner listerner;
    private final Context context;
    public TextView pdfTitle,pdfDesc;
    public ImageView imageView,download;

    public Adapter(@NonNull View itemView) {
        super(itemView);

        context = itemView.getContext();
        pdfTitle = itemView.findViewById(R.id.pdf_name);
        pdfDesc = itemView.findViewById(R.id.bookDes);
        imageView = itemView.findViewById(R.id.bookImage);
        download = itemView.findViewById(R.id.download);
    }

    @Override
    public void onClick(View view) {
        listerner.onClick(view, getAdapterPosition(), false);
    }
}
