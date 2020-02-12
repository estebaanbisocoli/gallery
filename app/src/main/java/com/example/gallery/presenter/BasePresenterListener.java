package com.example.gallery.presenter;

public interface BasePresenterListener {
    void onError(String message);
    void onLoading();
    void hideLoading();
}
