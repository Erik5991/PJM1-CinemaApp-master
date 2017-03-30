package com.avans.students.cinemaapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avans.students.cinemaapp.R;
import com.avans.students.cinemaapp.adapters.ShowTimesFragmentViewPagerAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Package: CinemaApp
 * Created by rickl on 21-3-2017.
 */

public class HomeFragment extends BaseFragment {

    private TextView textViewDate;
    private ArrayList<ShowTimesFragment> viewPagerFragmentList;
    private ViewPager.OnPageChangeListener viewPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //When pagescrolled, get Fragment out of Arraylist based on postition. Sets the textViewDate with the DateString of the Fragment
            textViewDate.setText(viewPagerFragmentList.get(position).getDateString());

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textViewDate = (TextView) view.findViewById(R.id.textViewDate);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //ArrayList for storing the ShowTimesFragments
        viewPagerFragmentList = generateShowTimesFragments(mActivity);



        //Adapter for the viewPager.
        ShowTimesFragmentViewPagerAdapter viewPagerAdapter = new ShowTimesFragmentViewPagerAdapter(viewPagerFragmentList, getChildFragmentManager());
        //Gets the view that is inflated by onCreateView
        View view = getView();
        if(view != null) {
            ViewPager viewPager = (ViewPager) getView().findViewById(R.id.pager);
            viewPager.setAdapter(viewPagerAdapter); //Sets the viewPagerAdapter on ViewPager
            viewPager.addOnPageChangeListener(viewPagerChangeListener); //Sets the viewPagerChangeListener on ViewPager

            TabLayout tabLayout = (TabLayout) getView().findViewById(R.id.tab_layout);
            tabLayout.setupWithViewPager(viewPager); //Link the tabLayout with the viewPager, create Tabs from the titles of the pages from the ViewPager
        }
    }

    /**
     * Method for generating ShowTimesFragments in Arraylist
     * @param context for getting Locale Resource
     * @return ArrayList with ShowTimesFragments
     */
    private static ArrayList<ShowTimesFragment> generateShowTimesFragments(Context context){
        //Instance of calendar for looping through week days.
        Calendar calendar = Calendar.getInstance();

        //New arrayList for storing the Fragments of the ViewPager.
        ArrayList<ShowTimesFragment> viewPagerFragmentList = new ArrayList<>();

        //For loop for looping a week from now
        for(int i = 0; i < 7; i++){
            //Adds a new Instance of ShowTimesFragment with the Calendar Date to the viewPagerFragmentList
            viewPagerFragmentList.add(ShowTimesFragment.newInstance(context,calendar.getTime()));
            //Add a day to de calender
            calendar.add(Calendar.DATE, 1);
        }
        return viewPagerFragmentList;
    }

}
