package com.royalfriends.bookies;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.royalfriends.bookies.Categories.Catehgories_section;
import com.royalfriends.bookies.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getProfileData();

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            String feedback = "mailto:"+ Uri.encode("enterEmail")+"?subject="+Uri.encode("Feedback")+Uri.encode("");
            Uri uri = Uri.parse(feedback);
            intent.setData(uri);
            startActivity(Intent.createChooser(intent,"send email"));
        });
        binding.Hii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isvisible = binding.showLayout.getVisibility();
                if (isvisible == View.VISIBLE) {
                    binding.showLayout.setVisibility(View.GONE);
                } else {
                    binding.showLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isvisible = binding.showLayout.getVisibility();
                if (isvisible == View.VISIBLE) {
                    binding.showLayout.setVisibility(View.GONE);
//                    HI.setText("Hiiii");
                } else {
//                    HI.setText("Bye");
                    binding.showLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Catehgories_section.class);
                startActivity(intent);
            }
        });
        binding.Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });

        binding.News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });binding.About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VideoTutorial.class);
                startActivity(intent);
            }
        });binding.ReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RetrivePDFActivity.class);
                startActivity(intent);
            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout() {
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.drawable.baseline_warning_24)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout this app")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void getProfileData() {
        reference = FirebaseDatabase.getInstance().getReference("UserQuotes").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String getName = snapshot.child("name").getValue().toString();
                    binding.name.setText(getName);
                    String profileImage = snapshot.child("image").getValue().toString();
                    Glide.with(getApplicationContext()).load(profileImage).into(binding.profileImageView);
                    String getMobileNumber = snapshot.child("mobileNumber").getValue().toString();
                    binding.mobilenumber.setText(getMobileNumber);
                    String getEmail = snapshot.child("emailId").getValue().toString();
                    binding.email.setText(getEmail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}