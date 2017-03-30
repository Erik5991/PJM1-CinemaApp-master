package com.avans.students.cinemaapp.interfaces;

/**
 * Package: CinemaApp
 * Created by rickl on 29-3-2017.
 */

public interface RequestCallback<T> {

    void onComplete(T result);
    void onError();

}
