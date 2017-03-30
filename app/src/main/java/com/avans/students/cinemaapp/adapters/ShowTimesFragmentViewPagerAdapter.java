package com.avans.students.cinemaapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.avans.students.cinemaapp.fragments.ShowTimesFragment;

import java.util.ArrayList;

/**
 * Package: CinemaApp
 * Created by rickl on 23-3-2017.
 */

public class ShowTimesFragmentViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<ShowTimesFragment> fragmentList;

    public ShowTimesFragmentViewPagerAdapter(ArrayList<ShowTimesFragment> fragmentList, FragmentManager fm) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
