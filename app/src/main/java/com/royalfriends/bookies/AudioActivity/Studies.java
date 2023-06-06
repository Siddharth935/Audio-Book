package com.royalfriends.bookies.AudioActivity;

import static com.royalfriends.bookies.R.font.alegreya_sc;
import static com.royalfriends.bookies.R.layout.font_colour;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jean.jcplayer.model.JcAudio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.royalfriends.bookies.R;
import com.royalfriends.bookies.Song;
import com.royalfriends.bookies.databinding.ActivityStudiesBinding;

import java.util.ArrayList;

public class Studies extends AppCompatActivity {

    private ActivityStudiesBinding binding;
    ArrayList<JcAudio> jcAudios = new ArrayList<>();
    ArrayList<String> arrayListSongsName = new ArrayList<>();
    ArrayList<String> arrayListSongsUrl = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    Query query;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("StudiesAudio");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudiesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        query = databaseReference.orderByChild("songName");
        //we will request the files with the filename, if filename exists then it will show in the recyclerView
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    binding.progressBar.setVisibility(View.GONE);
                    retrieveSongs();
                }else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(Studies.this, "File Not Found!", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        binding.myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.jcplayer.playAudio(jcAudios.get(position));
                binding.jcplayer.setVisibility(View.VISIBLE);

//                binding.jcplayer.createNotification();
                binding.jcplayer.createNotification(R.drawable.audiobook);

            }
        });
     }
    private void retrieveSongs() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                    Song songObj = ds.getValue(Song.class);
                    arrayListSongsName.add(songObj.getSongName());
                    arrayListSongsUrl.add(songObj.getSongUrl());
                    jcAudios.add(JcAudio.createFromURL(songObj.getSongName(),songObj.getSongUrl()));

                }
                arrayAdapter = new ArrayAdapter<String>(Studies.this, android.R.layout.simple_list_item_1,arrayListSongsName){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position,convertView,parent);
                        TextView textView = (TextView)view.findViewById(android.R.id.text1);
                        textView.setSingleLine(true);
                        textView.setMaxLines(1);
                        return view;
                    }
                };
                binding.jcplayer.initPlaylist(jcAudios,null);
                binding.myListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}