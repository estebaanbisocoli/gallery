package com.example.gallery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gallery.R;
import com.example.gallery.model.Album;

import java.util.List;

public class GalleryRecyclerViewAdapter extends RecyclerView.Adapter<GalleryRecyclerViewAdapter.GalleryViewHolder> {
    private final List<Album> albums;
    private final OnAlbumClickListener onClickListener;

    public GalleryRecyclerViewAdapter(List<Album> albums, OnAlbumClickListener onClickListener) {
        this.albums = albums;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gallery, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        Button button = holder.itemView.findViewById(R.id.galleryButton);
        button.setOnClickListener((v) -> this.onClickListener.onClick(albums.get(position)));
        button.setText(albums.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {

        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnAlbumClickListener {

        void onClick(Album album);
    }
}
