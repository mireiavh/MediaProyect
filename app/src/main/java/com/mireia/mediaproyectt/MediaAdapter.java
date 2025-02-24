package com.mireia.mediaproyectt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    private List<MediaItem> mediaItems;
    private OnItemClickListener listener;

    public MediaAdapter(List<MediaItem> mediaItems, OnItemClickListener listener) {
        this.mediaItems = mediaItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        MediaItem mediaItem = mediaItems.get(position);
        holder.titleTextView.setText(mediaItem.getTitle());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(mediaItem));
    }

    @Override
    public int getItemCount() {
        return mediaItems.size();
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(android.R.id.text1);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MediaItem mediaItem);
    }
}
