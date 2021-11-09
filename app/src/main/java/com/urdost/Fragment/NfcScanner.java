package com.urdost.Fragment;

import android.os.Bundle;
import android.os.Handler;

import com.urdost.Activity.WebNfcCodeActivate;
import com.urdost.constants.BaseActivity;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class NfcScanner extends BaseActivity implements  ZXingScannerView.ResultHandler {
    ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(zXingScannerView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        zXingScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        new Handler().postDelayed(new Runnable() {


            @Override

            public void run() {
                Bundle bundle = new Bundle();
                bundle.putString("Result", rawResult.getText());
                goToActivityWithFinish(context, WebNfcCodeActivate.class,bundle);
            }
        },100);

        //  bundle.putString("from", "login");
        //  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rawResult.getText()));
        //  browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //  startActivity(browserIntent);
        //MainActivity.tvresult.setText(rawResult.getText());
        onBackPressed();
    }
}
