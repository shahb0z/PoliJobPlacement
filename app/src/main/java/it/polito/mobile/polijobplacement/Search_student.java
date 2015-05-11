package it.polito.mobile.polijobplacement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 5/9/2015.
 */
public class Search_student extends Fragment {
    private FragmentPageAdapter_Search_Student fpgr;
    ViewPager vp;

    public static Search_student newInstance() {
        return new Search_student();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tabbed, container, false);
        fpgr = new FragmentPageAdapter_Search_Student(getChildFragmentManager());


        vp = (ViewPager) v.findViewById(R.id.pager);
        vp.setAdapter(fpgr);

        /*slidingTabLayout = (SlidingTabLayout) inflater.inflate(R.id.sliding_tabs, container , false);
		slidingTabLayout.setDistributeEvenly(true);
		slidingTabLayout.setViewPager(mViewPager);*/

        return v;
    }


}
