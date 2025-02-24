package com.mireia.mediaproyectt;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MediaAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private MediaAdapter mediaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<MediaItem> mediaItems = new ArrayList<>();
        mediaItems.add(new MediaItem("Video 1", 0, "android.resource://" + getPackageName() + "/" + R.raw.sample_video));
        mediaItems.add(new MediaItem("Video 2", 0, "android.resource://" + getPackageName() + "/" + R.raw.sample_video2));
        mediaItems.add(new MediaItem("Audio 1", 1, "android.resource://" + getPackageName() + "/" + R.raw.sample_audio));
        mediaItems.add(new MediaItem("Audio 2", 1, "android.resource://" + getPackageName() + "/" + R.raw.sample_audio2));
        mediaItems.add(new MediaItem("Web 1", 2, "https://www.google.com"));
        mediaItems.add(new MediaItem("Web 2", 2, "https://www.google.com"));

        mediaAdapter = new MediaAdapter(mediaItems, this);
        recyclerView.setAdapter(mediaAdapter);
    }

    @Override
    public void onItemClick(MediaItem mediaItem) {
        MediaDialogFragment dialog = MediaDialogFragment.newInstance(mediaItem);
        dialog.show(getSupportFragmentManager(), "MediaDialogFragment");
    }
}