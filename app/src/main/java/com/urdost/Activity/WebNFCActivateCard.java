package com.urdost.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

import androidx.annotation.Nullable;

import com.urdost.R;
import com.urdost.constants.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebNFCActivateCard extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.web_view)
    WebView webView;
    Bundle bundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_nfc_card);
        ButterKnife.bind(this);
        tvTitle.setText("NFC ");
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);
        webView.setWebViewClient(new MyWebViewClient());
        shouldOverrideUrlLoading(webView, bundle.getString("Result"));
        String url = bundle.getString("Result");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url); // load the url on the web view

    }
    // custom web view client class who extends WebViewClient
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url); // load the url

            return true;
        }
    }
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith("tel:")) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
            startActivity(intent);
            view.reload();
            return true;
        }

        view.loadUrl(url);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                       finish();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.img_back)
    public void onClick() {
     onBackPressed();
    }


}