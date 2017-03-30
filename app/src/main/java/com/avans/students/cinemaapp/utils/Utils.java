package com.avans.students.cinemaapp.utils;

import android.content.Context;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Package: CinemaApp
 * Created by rickl on 28-3-2017.
 */

public class Utils {

    /**
     * Method for returning a generic dateFormat
     * @return format of date like: maandag 3 maart
     * URL: http://javatechniques.com/blog/dateformat-and-simpledateformat-examples/
     */
    public static String dateFormatEEEEdMMMM(){
        return "EEEE d MMMM";
    }

    /**
     * Method for returning a generic dateFormat
     * @return format of date like: maandag
     * URL: http://javatechniques.com/blog/dateformat-and-simpledateformat-examples/
     */
    public static String dateFormatEEE(){
        return "EEEE";
    }

    /**
     * Method for creating a simpleDateFormater with device Locale
     * @param format Date format in String Like "EEE" or "EEEE d MMMM"
     * @param context Context needed for method getCurrentLocale
     * @return a SimpleDateFormat witb specified format and locale
     */
    public static SimpleDateFormat getSimpleDateFormat(String format, Context context){
        return new SimpleDateFormat(format, Utils.getCurrentLocale(context));
    }

    /**
     * Method for reading the currentLocale of device
     * @param context needed for getResources()
     * @return the current locale of phone
     * URL: http://stackoverflow.com/questions/14389349/android-get-current-locale-not-default
     */
    public static Locale getCurrentLocale(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return context.getResources().getConfiguration().getLocales().get(0);
        } else{
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }

    /**
     * Capitalize each first letter of each word
     * @param line the string that must be capitalized
     * @return the capitalized input string
     * URL: http://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string
     */
    public static  String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
