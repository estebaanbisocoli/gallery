package com.example.gallery.adapter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gallery.R;
import com.example.gallery.model.Photo;
import com.example.gallery.utils.BitmapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<PhotosRecyclerViewAdapter.PhotosViewHolder> {

    private final List<Photo> photos;
    private final Handler handler;
    private HashMap<Integer, Bitmap> bitmapsCache = new HashMap<>();

    public PhotosRecyclerViewAdapter(List<Photo> photos) {
        this.photos = photos;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_photo_thumbmail, parent, false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull PhotosViewHolder holder) {

    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.thumbmailTextView);
        textView.setText(photos.get(position).getTitle());
        if (bitmapsCache.get(position) != null) {
            ImageView imageView = holder.itemView.findViewById(R.id.thumbmailImageView);
            imageView.setImageBitmap(bitmapsCache.get(position));
        } else {
            BitmapUtils.fetchImage(photos.get(position).getThumbnailUrl(), (bitmap) -> {
                handler.post(() -> {
                    ImageView imageView = holder.itemView.findViewById(R.id.thumbmailImageView);
                    imageView.setImageBitmap(bitmap);
                    this.bitmapsCache.put(position, bitmap);
                });
            });
        }


    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class PhotosViewHolder extends RecyclerView.ViewHolder {

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
