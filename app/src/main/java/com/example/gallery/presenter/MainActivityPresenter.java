package com.example.gallery.presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.gallery.model.Album;
import com.fasterxml.jackson.core.type.TypeReference;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MainActivityPresenter extends BasePresenter {
    private final MainActivityPresenterListerner presenterListener;
    private static final String ALBUMS_URL = "https://jsonplaceholder.typicode.com/albums/";

    public MainActivityPresenter(MainActivityPresenterListerner mainActivityPresenterListerner) {
        super();
        this.presenterListener = mainActivityPresenterListerner;
    }

    public void loadAlbums() {
        presenterListener.onLoading();
        getRequest(ALBUMS_URL, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            presenterListener.hideLoading();
                            presenterListener.onError(e.getMessage());

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                List<Album> albums = objectMapper.readValue(response.body().string(), new TypeReference<List<Album>>() {
                });
                handler.post(() -> {
                    presenterListener.hideLoading();
                    presenterListener.onGalleryItemsFetched(albums);
                });
            }
        });
    }
}
