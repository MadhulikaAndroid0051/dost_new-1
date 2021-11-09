package com.urdost.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.urdost.Fragment.AadharCardBack;
import com.urdost.Fragment.AadharCardFront;

public class AadharcardAdapter extends FragmentPagerAdapter {


    public AadharcardAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AadharCardFront();
            case 1:
                return new AadharCardBack();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
