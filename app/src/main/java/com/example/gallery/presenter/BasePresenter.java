package com.example.gallery.presenter;

import android.os.Handler;
import android.os.Looper;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

abstract class BasePresenter {
    private final OkHttpClient okHttpClient;
    final ObjectMapper objectMapper;
    protected Handler handler;

    BasePresenter() {
        this.okHttpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
        this.handler = new Handler(Looper.getMainLooper());


    }

    void getRequest(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
