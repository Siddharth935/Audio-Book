package com.royalfriends.bookies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VideoTutorial extends AppCompatActivity {
    private VideoView video;
    private MediaController mediaController;

    ProgressDialog pd;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference();
    private DatabaseReference childReference = reference.child("urlForCustomerTutorial");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_tutorial);
        video = findViewById(R.id.video);
        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(video);

        video.setMediaController(mediaController);
//        video.resolveAdjustedSize()
        video.requestFocus();

        pd = new ProgressDialog(VideoTutorial.this);
        pd.setMessage("Buffering Please wait");
        pd.show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String message = snapshot.getValue(String.class);
                Uri uri = Uri.parse(message);
                video.setVideoURI(uri);
                video.start();
                video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        pd.dismiss();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}