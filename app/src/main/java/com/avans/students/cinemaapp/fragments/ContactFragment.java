package com.avans.students.cinemaapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.avans.students.cinemaapp.R;
import com.avans.students.cinemaapp.utils.MapsActivity;

/**
 * Package: CinemaApp
 * Created by rickl on 22-3-2017.
 */

public class ContactFragment extends Fragment implements View.OnClickListener {

    Button mapsKnop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapsKnop = (Button) getView().findViewById(R.id.mapsKnop);
        mapsKnop.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);
    }
}







