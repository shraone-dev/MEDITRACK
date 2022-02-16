package com.example.maditrack.MediTracklFolder;

import com.example.maditrack.R;

public enum Help {

    Medicine(R.string.Medicine, R.layout.helpscreen_medicine),
    Ice(R.string.Ice, R.layout.helpscreen_ice),
    Category(R.string.Category, R.layout.helpscreen_category),
    Appointment(R.string.Appointment, R.layout.helpscreen_appointment);



    private int mTitleResId;
    private int mLayoutResId;

    Help(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
