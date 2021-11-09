package com.urdost.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.urdost.R;
import com.urdost.constants.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NFcCardActivate extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.card_view1)
    CardView cardView1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_actionnfc, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }



    @OnClick(R.id.card_view1)
    public void onClick() {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, 100);
        else
            goToActivity(NfcScanner.class, null);
    }
}
