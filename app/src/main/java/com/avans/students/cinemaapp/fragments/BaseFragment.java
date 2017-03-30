package com.avans.students.cinemaapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Package: CinemaApp
 * Created by rickl on 22-3-2017.
 * Extend Class for all Fragments we use, now we could use mActivity after onActivityCreated in each Fragment that extends This.
 */

public class BaseFragment extends Fragment {

    protected AppCompatActivity mActivity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (AppCompatActivity) getActivity();
    }

}
