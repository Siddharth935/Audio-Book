package com.royalfriends.bookies;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.royalfriends.bookies.databinding.ActivityPdfBinding;

import java.io.File;

public class PdfActivity extends AppCompatActivity {

    String filePath = "";
    private ActivityPdfBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        filePath = getIntent().getStringExtra("fileurl");
        File file = new File(filePath);
        Uri path = Uri.fromFile(file);
        binding.pdfView.fromUri(path).load();
    }
}