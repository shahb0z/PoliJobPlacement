package it.polito.mobile.polijobplacement;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
