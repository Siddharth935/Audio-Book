package com.royalfriends.bookies.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.royalfriends.bookies.AudioActivity.Career;
import com.royalfriends.bookies.AudioActivity.Comics;
import com.royalfriends.bookies.AudioActivity.Health;
import com.royalfriends.bookies.AudioActivity.Studies;
import com.royalfriends.bookies.AudioActivity.Travel;
import com.royalfriends.bookies.AudioActivity.knowledge;
import com.royalfriends.bookies.R;
import com.royalfriends.bookies.databinding.ActivityCatehgoriesSectionBinding;

public class Catehgories_section extends AppCompatActivity {

    private ActivityCatehgoriesSectionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCatehgoriesSectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.Studies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Catehgories_section.this, Studies.class);
                startActivity(intent);
            }
        });
        binding.Career.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Catehgories_section.this, Career.class);
                startActivity(intent);
            }
        });
        binding.knowlegde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Catehgories_section.this, knowledge.class);
                startActivity(intent);
            }
        });
        binding.comic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Catehgories_section.this, Comics.class);
                startActivity(intent);
            }
        });
        binding.Health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Catehgories_section.this, Health.class);
                startActivity(intent);
            }
        });binding.Travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Catehgories_section.this, Travel.class);
                startActivity(intent);
            }
        });
    }
}