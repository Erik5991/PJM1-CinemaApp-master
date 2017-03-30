package com.avans.students.cinemaapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.avans.students.cinemaapp.R;
import com.avans.students.cinemaapp.adapters.MovieListAdapter;
import com.avans.students.cinemaapp.data.TMDBApi;
import com.avans.students.cinemaapp.interfaces.RequestCallback;
import com.avans.students.cinemaapp.objects.Movie;
import com.avans.students.cinemaapp.utils.Utils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Package: CinemaApp
 * Created by rickl on 22-3-2017.
 */

public class ShowTimesFragment extends BaseFragment {

    private String datum;
    private ArrayList<Movie> movieList;
    private MovieListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_showtimes, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        movieList = new ArrayList<>();
        movieList.add(new Movie("Test1"));
        movieList.add(new Movie("Test2"));
        movieList.add(new Movie("Test3"));
        movieList.add(new Movie("Test4"));
        movieList.add(new Movie("Test5"));

        TMDBApi tmdbApi = new TMDBApi(mActivity.getApplicationContext());
        tmdbApi.getNowPlayingMovies(new RequestCallback<ArrayList<Movie>>() {
            @Override
            public void onComplete(ArrayList<Movie> result) {
                movieList.clear();
                movieList.addAll(result);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError() {
                Log.d("DEBUG", "onError");
            }
        });

        adapter = new MovieListAdapter(movieList, R.layout.movie_item, mActivity);

        Bundle b = getArguments();
        Long datumLong= b.getLong("date", 0L);

        Date date = new Date();
        date.setTime(datumLong);

        datum = Utils.capitalize(Utils.getSimpleDateFormat(Utils.dateFormatEEEEdMMMM(), mActivity).format(date));

        View view = getView();
        if(view != null) {
            ListView listView = (ListView) view.findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }

    /**
     * Create a new Instance of ShowTimesFragment with custom bundle to Fragment
     * @param context needed for getting CurrentLocale
     * @param date needed for the date of Fragment
     * @return a ShowTimesFragment of the specific date
     */
    public static ShowTimesFragment newInstance(Context context, Date date){

        ShowTimesFragment showTimesFragment = new ShowTimesFragment();

        Bundle b = new Bundle();
        b.putLong("date", date.getTime());
        b.putString("title", Utils.getSimpleDateFormat(Utils.dateFormatEEE(), context).format(date));

        showTimesFragment.setArguments(b);

        return showTimesFragment;
    }

    /**
     * Returns the title from the arguments.
     * @return String with title of ShowTimesFragment.
     */
    public String getTitle(){
        Bundle b = getArguments();
        return b.getString("title", "");
    }

    public String getDateString() {
        return datum;
    }
}