package com.royalfriends.bookies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class RetrivePDFActivity extends AppCompatActivity {
    RecyclerView pdfRecyclerView;
    private DatabaseReference pRef;
    Query query;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_pdfactivity);
        displayPdfs();
    }
    private void displayPdfs() {

        pRef = FirebaseDatabase.getInstance().getReference("pdf");
        pdfRecyclerView = findViewById(R.id.recyclerView);
        pdfRecyclerView.setHasFixedSize(true);
        pdfRecyclerView.setLayoutManager(new LinearLayoutManager(RetrivePDFActivity.this, LinearLayoutManager.VERTICAL, false));

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        query = pRef.orderByChild("filename");
        //we will request the files with the filename, if filename exists then it will show in the recyclerView
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    progressBar.setVisibility(View.GONE);
                    showPdf();
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RetrivePDFActivity.this, ":", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
    private void showPdf() {
        FirebaseRecyclerOptions<FileinModel> options = new FirebaseRecyclerOptions.Builder<FileinModel>()
                .setQuery(query, FileinModel.class)
                .build();
        FirebaseRecyclerAdapter<FileinModel, Adapter> adapter = new FirebaseRecyclerAdapter<FileinModel, Adapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull  Adapter holder, int position, @NonNull FileinModel model) {

                progressBar.setVisibility(View.GONE);
                holder.pdfTitle.setText(model.getFilename());
                holder.pdfDesc.setText(model.getFileDescription());
                Glide.with(getApplicationContext()).load(model.getFileImge()).into(holder.imageView);
                holder.download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setType("application/pdf");
                        intent.setData(Uri.parse(model.getFileurl()));
//                        Intent intent = new Intent(RetrivePDFActivity.this, PdfActivity.class);
//                        intent.putExtra("fileurl", model.getFileurl());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public Adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdf_item,parent,false);
                Adapter holder = new Adapter(view);
                return holder;
            }
        };

        pdfRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }


}