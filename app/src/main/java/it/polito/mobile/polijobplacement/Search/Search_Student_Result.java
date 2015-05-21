package it.polito.mobile.polijobplacement.Search;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.polito.mobile.polijobplacement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_Student_Result extends android.support.v4.app.Fragment {

    private static String f;
    private static String d;
    private static String l;
    private static String lev;
    private static String loc;
    private static String skill;
    public Search_Student_Result() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search__student__result, container, false);
    }


    public static Search_Student_Result newInstance(String field, String degree, String level, String s, String s1, String s2) {
        f = field;
        d = degree;
        lev = level;
        l = s2;
        loc = s;
        skill = s1;
        return new Search_Student_Result();
    }
}
