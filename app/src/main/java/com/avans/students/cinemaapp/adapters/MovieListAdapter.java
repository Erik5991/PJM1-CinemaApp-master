package com.avans.students.cinemaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.avans.students.cinemaapp.R;
import com.avans.students.cinemaapp.objects.Movie;
import com.avans.students.cinemaapp.utils.NetworkController;

import java.util.ArrayList;

/**
 * Package: CinemaApp
 * Created by rickl on 27-3-2017.
 */

public class MovieListAdapter extends ArrayAdapter<Movie> {

    private ArrayList<Movie> movieList;
    private Context mContext;
    private int listItem;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtVersion;
        ImageView info;
        NetworkImageView networkImageView;
    }

    public MovieListAdapter(ArrayList<Movie> movieList, int listItem, Context context) {
        super(context, listItem, movieList);
        this.movieList = movieList;
        this.mContext=context;
        this.listItem = listItem;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = movieList.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(listItem, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.movieTitle);
            viewHolder.networkImageView = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
            //viewHolder.txtType = (TextView) convertView.findViewById(R.id.type);
            //viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.version_number);
            //viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(movie.getTitle());
        if(movie.getPoster_path() != null && !movie.getPoster_path().isEmpty() && !movie.getPoster_path().equals("null")) {
            viewHolder.networkImageView.setImageUrl("https://image.tmdb.org/t/p/w500" + movie.getPoster_path(), NetworkController.getInstance(mContext).getImageLoader());
        }
        //viewHolder.txtType.setText(dataModel.getType());
        //viewHolder.txtVersion.setText(dataModel.getVersion_number());
        //viewHolder.info.setOnClickListener(this);
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
