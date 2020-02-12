package com.example.gallery.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BitmapUtils {

    static OkHttpClient okHttpClient = new OkHttpClient();

    public static void fetchImage(String path, ImageFetch callback) {
        Request request = new Request.Builder().url(path).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
              Bitmap bitmap =  BitmapFactory.decodeStream(response.body().byteStream());
              callback.onImageFetched(bitmap);
            }
        });

    }

    public interface ImageFetch {
        void onImageFetched(Bitmap bitmap);
    }
}
