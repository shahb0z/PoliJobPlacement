package it.polito.mobile.polijobplacement.Search;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.polito.mobile.polijobplacement.R;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class Company_Home extends android.support.v4.app.Fragment {


    public Company_Home() {
        // Required empty public constructor
    }

    public static Company_Home newInstance() {
        return new Company_Home();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company__home, container, false);
    }


}
