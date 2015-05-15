package it.polito.mobile.polijobplacement;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;

import android.view.LayoutInflater;

import android.view.View;
import android.content.Context;
import android.view.ViewGroup;

public class job_newoffer  extends Fragment {

   /* public static Fragment  newInstance(Context context) {
        Job_NewOffer f = new Job_NewOffer();

        return f;}*/
  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    { View rootView = inflater.inflate(R.layout.job_newoffer, container, false);
        return rootView;
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater,

                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.job_newoffer, container, false);  /// null or false

        //Test  test

    }
}






