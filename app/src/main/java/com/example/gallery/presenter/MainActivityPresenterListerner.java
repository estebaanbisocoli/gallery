package com.example.gallery.presenter;

import com.example.gallery.model.Album;

import java.util.List;

public interface MainActivityPresenterListerner extends BasePresenterListener {
    void onGalleryItemsFetched(List<Album> albums);

}
