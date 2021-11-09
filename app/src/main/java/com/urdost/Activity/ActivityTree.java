package com.urdost.Activity;


import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.urdost.Fragment.MainDashboard;
import com.urdost.Fragment.TreeView;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.constants.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityTree extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.cont)
    ConstraintLayout cont;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        ButterKnife.bind(this);
        replaceFragment(new TreeView(), "Hi, " + PreferencesManager.getInstance(context).getFullname());
    }

    public Fragment currentFragment;

    public void replaceFragment(Fragment setFragment, String title) {
        new Handler().postDelayed(() -> {
            currentFragment = setFragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, setFragment, title);
            tvTitle.setText(title);
            transaction.commitAllowingStateLoss();
        }, 200);
    }


    @OnClick(R.id.img_back)
    public void onClick() {
    }
}
