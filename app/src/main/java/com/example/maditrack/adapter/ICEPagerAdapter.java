package com.example.maditrack.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.maditrack.fragment.DoctorFragment;
import com.example.maditrack.fragment.EmergencyFragment;
import com.example.maditrack.fragment.NOKFragment;

public class ICEPagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public ICEPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NOKFragment nokFragment = new NOKFragment();
                return nokFragment;
            case 1:
                DoctorFragment doctorFragment = new DoctorFragment();
                return doctorFragment;
            default:
                EmergencyFragment bookingFragment = new EmergencyFragment();
                return bookingFragment;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
