package com.example.gallery.presenter;

import com.example.gallery.model.Photo;
import com.fasterxml.jackson.core.type.TypeReference;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PhotosActivityPresenter extends BasePresenter {
    private final PhotosActivityPresenterListener presenterListener;
    private final static String PHOTOS_URL = "https://jsonplaceholder.typicode.com/albums/{id_album}/photos";
    public PhotosActivityPresenter(PhotosActivityPresenterListener photosActivityPresenterListener) {
        super();
        this.presenterListener = photosActivityPresenterListener;
    }
    public void fetchPhotos(int albumId) {
        presenterListener.onLoading();
        getRequest(PHOTOS_URL.replace("{id_album}", String.valueOf(albumId)), new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
               presenterListener.onError(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                List<Photo> photos = objectMapper.readValue(response.body().string(), new TypeReference<List<Photo>>() {});
                handler.post(() -> {
                    presenterListener.onPhotosFetched(photos);
                    presenterListener.hideLoading();
                });
            }
        });
    }
}
