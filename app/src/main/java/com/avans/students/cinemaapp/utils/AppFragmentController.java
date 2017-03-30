package com.avans.students.cinemaapp.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.avans.students.cinemaapp.MainActivity;
import com.avans.students.cinemaapp.R;
import com.avans.students.cinemaapp.fragments.BenefitCardFragment;
import com.avans.students.cinemaapp.fragments.ContactFragment;
import com.avans.students.cinemaapp.fragments.DealsEventsFragment;
import com.avans.students.cinemaapp.fragments.HomeFragment;
import com.avans.students.cinemaapp.fragments.NowInCinemaFragment;
import com.avans.students.cinemaapp.fragments.ToBeExpectedFragment;

/**
 * Package: CinemaApp
 * Created by rickl on 21-3-2017.
 */

public class AppFragmentController {

    public static FragmentManager sFragmentManager;

    public AppFragmentController(FragmentManager sFragmentManager) {
        AppFragmentController.sFragmentManager = sFragmentManager;
    }

    public static Fragment getHomeFragment() {
        MainActivity.sActionBar.setTitle(R.string.nav_home);
        return attemptAddFragment(new HomeFragment(), false);
    }

    public static Fragment getBenefitCardFragment() {
        MainActivity.sActionBar.setTitle(R.string.nav_benefit_card);
        return attemptAddFragment(new BenefitCardFragment(), false);
    }

    public static Fragment getContactFragment() {
        MainActivity.sActionBar.setTitle(R.string.nav_contact);
        return attemptAddFragment(new ContactFragment(), false);
    }

    public static Fragment getDealsEventsFragment() {
        MainActivity.sActionBar.setTitle(R.string.nav_deals_events);
        return attemptAddFragment(new DealsEventsFragment(), false);
    }

    public static Fragment getNowInCinemaFragment() {
        MainActivity.sActionBar.setTitle(R.string.nav_now_in_cinema);
        return attemptAddFragment(new NowInCinemaFragment(), false);
    }

    public static Fragment getToBeExpectedFragment() {
        MainActivity.sActionBar.setTitle(R.string.nav_to_be_expected);
        return attemptAddFragment(new ToBeExpectedFragment(), false);
    }

    /**
     * Replaces the fragment in the Frameholder with the new fragment, default add to backstack.
     * @param fragment Specify fragment for in Frameholder.
     * @return returns the Fragment which is added to the frameHolder.
     */
    private static Fragment attemptAddFragment(Fragment fragment) {
        return attemptAddFragment(fragment, true);
    }

    /**
     * Replaces the fragment in the Frameholder with the new fragment.
     * @param fragment Specify fragment for in Frameholder.
     * @param addToBackStack True if must be added to backstack.
     * @return returns the Fragment which is added to the frameHolder.
     */
    private static Fragment attemptAddFragment(Fragment fragment, boolean addToBackStack) {
        try {
            FragmentTransaction mFragmentTransaction = sFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.frameHolder, fragment);
            if (addToBackStack) {
                mFragmentTransaction.addToBackStack(null);
            }
            mFragmentTransaction.commit();
        } catch (Exception e) {
            Log.e("---Fragment error--", "" + e);
        }
        return fragment;
    }


}
