package com.avans.students.cinemaapp.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.avans.students.cinemaapp.R;
import com.avans.students.cinemaapp.interfaces.RequestCallback;
import com.avans.students.cinemaapp.objects.Movie;
import com.avans.students.cinemaapp.utils.NetworkController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Package: CinemaApp
 * Created by rickl on 28-3-2017.
 */

public class TMDBApi {

    private static final String TAG = TMDBApi.class.getName();

    //private static String API_KEY = "";
    private static String API_KEY = "48e8b9d4f8d68070c74d1af0c4a46919";
    private static final String URL_TMDB_API = "https://api.themoviedb.org/3";
    private static final String URL_NEW_MOVIES = "/movie/upcoming";
    private static final String URL_MOVIE_INFO = "/movie/";
    private static final String URL_MOVIE_INFO_ACTORS = "/credits";
    private static final String URL_MOVIES_NOW_PLAYING = "/movie/now_playing";

    private NetworkController networkController;

    public TMDBApi(Context context) {
        //API_KEY = context.getString(R.string.tmdb_api_key);
        API_KEY = "48e8b9d4f8d68070c74d1af0c4a46919";
        networkController = NetworkController.getInstance(context);
    }

    public void getNowPlayingMovies(final RequestCallback<ArrayList<Movie>> requestCallback){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_TMDB_API + URL_MOVIES_NOW_PLAYING + "?api_key="+API_KEY, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, String.valueOf(response));
                try {
                    ArrayList<Movie> movieArrayList = new ArrayList<>();
                    JSONArray jsonArrayResults = response.getJSONArray("results");
                    for(int i = 0; i < jsonArrayResults.length(); i++){
                        JSONObject jsonObject = jsonArrayResults.getJSONObject(i);

                        int id = jsonObject.getInt("id");
                        String poster_path = jsonObject.getString("poster_path");
                        Boolean adult = jsonObject.getBoolean("adult");
                        String overview = jsonObject.getString("overview");
                        String release_date = jsonObject.getString("release_date");
                        ArrayList<Integer> genre_ids = new ArrayList<>();
                        String original_title = jsonObject.getString("original_title");
                        String original_language = jsonObject.getString("original_language");
                        String title = jsonObject.getString("title");
                        String backdrop_path = jsonObject.getString("backdrop_path");
                        Double popularity = jsonObject.getDouble("popularity");
                        int vote_count = jsonObject.getInt("vote_count");
                        Boolean video = jsonObject.getBoolean("video");
                        Double vote_average = jsonObject.getDouble("vote_average");

                        Movie movie = new Movie(id, poster_path, adult, overview, release_date, genre_ids, original_title, original_language, title, backdrop_path, popularity,vote_count, video, vote_average);
                        movieArrayList.add(movie);
                    }

                    requestCallback.onComplete(movieArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    requestCallback.onError();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestCallback.onError();
            }
        });
        networkController.addToRequestQueue(jsonObjectRequest);
    }
}
