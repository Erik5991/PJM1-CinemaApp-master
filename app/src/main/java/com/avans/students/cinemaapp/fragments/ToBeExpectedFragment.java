package com.avans.students.cinemaapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avans.students.cinemaapp.R;

/**
 * Package: CinemaApp
 * Created by rickl on 22-3-2017.
 */

public class ToBeExpectedFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tobeexpected, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(mActivity.getPackageName(), "TEST");
    }

}
