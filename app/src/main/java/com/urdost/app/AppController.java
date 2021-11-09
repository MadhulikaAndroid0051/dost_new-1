package com.urdost.app;

import android.app.Application;

import com.urdost.R;
import com.urdost.common.ConnectivityReceiver;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppController extends Application {
    private static AppController mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CenturyGothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .disableCustomViewInflation()
                .build());
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}

