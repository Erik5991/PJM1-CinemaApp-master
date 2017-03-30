package com.avans.students.cinemaapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Package: CinemaApp
 * Created by rickl on 29-3-2017.
 * URL: https://developer.android.com/training/volley/requestqueue.html#singleton
 * URL: https://karanbalkar.com/2014/05/tutorial-81-using-volley-networking-library-in-android/
 */

public class NetworkController {

    private static NetworkController mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private NetworkController(Context context) {
        mRequestQueue =  Volley.newRequestQueue(context);

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized NetworkController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkController(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        mRequestQueue.add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}