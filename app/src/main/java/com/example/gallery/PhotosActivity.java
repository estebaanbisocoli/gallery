package com.example.gallery;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gallery.adapter.PhotosRecyclerViewAdapter;
import com.example.gallery.model.Album;
import com.example.gallery.model.Photo;
import com.example.gallery.presenter.PhotosActivityPresenter;
import com.example.gallery.presenter.PhotosActivityPresenterListener;

import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends BaseActivity implements PhotosActivityPresenterListener {
    private Album album;
    private PhotosActivityPresenter photosActivityPresenter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        this.album = getIntent().getParcelableExtra(Album.EXTRA_NAME);
        this.photosActivityPresenter = new PhotosActivityPresenter(this);
        setUpRecyclerView();
        photosActivityPresenter.fetchPhotos(album.getId());
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.photosRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onPhotosFetched(List<Photo> photos) {
        this.recyclerView.setAdapter(new PhotosRecyclerViewAdapter(photos));
    }
}
