package com.example.gallery.presenter;

import com.example.gallery.model.Photo;

import java.util.List;

public interface PhotosActivityPresenterListener extends BasePresenterListener{
    void onPhotosFetched(List<Photo> photos);
}
