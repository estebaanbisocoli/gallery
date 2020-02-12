package com.example.gallery;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gallery.presenter.BasePresenterListener;

public abstract class BaseActivity extends AppCompatActivity implements BasePresenterListener {

    private ProgressDialog dialog;
    private Dialog errorDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onError(String message) {
        runOnUiThread(() -> {
            Log.d("BASEActibvity", message);
            hideLoading();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("An error has ocurred");
            builder.setCancelable(true);
            builder.create().show();
        });

    }

    @Override
    public void onLoading() {
     this.dialog = ProgressDialog.show(BaseActivity.this, "",
                "Loading. Please wait...", true);
    }
    @Override
    public void hideLoading() {
        runOnUiThread(() -> {
            if(dialog != null) dialog.hide();
        });
    }
}
