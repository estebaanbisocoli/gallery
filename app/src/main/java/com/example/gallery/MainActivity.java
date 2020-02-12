package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gallery.adapter.GalleryRecyclerViewAdapter;
import com.example.gallery.model.Album;
import com.example.gallery.presenter.MainActivityPresenter;
import com.example.gallery.presenter.MainActivityPresenterListerner;

import java.util.List;

public class MainActivity extends BaseActivity implements MainActivityPresenterListerner, GalleryRecyclerViewAdapter.OnAlbumClickListener {

    private MainActivityPresenter mainActivityPresenter;
    private RecyclerView recyclerView;
    private GalleryRecyclerViewAdapter galleryRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mainActivityPresenter = new MainActivityPresenter(this);
        setupRecyclerView();
        this.mainActivityPresenter.loadAlbums();
    }

    private void setupRecyclerView() {
        this.recyclerView = findViewById(R.id.galleryRecyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onGalleryItemsFetched(List<Album> albums) {
        galleryRecyclerViewAdapter = new GalleryRecyclerViewAdapter(albums, this);
        recyclerView.setAdapter(galleryRecyclerViewAdapter);
    }


    @Override
    public void onClick(Album album) {
        Intent activityIntent = new Intent(this, PhotosActivity.class);
        activityIntent.putExtra(Album.EXTRA_NAME, album);
        startActivity(activityIntent);
    }
}
