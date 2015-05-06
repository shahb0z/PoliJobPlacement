package it.polito.mobile.polijobplacement;

/**
 * Created by Admin on 5/6/2015.
 */


import java.util.Locale;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabbedFragment extends Fragment {

    public static final String TAG = TabbedFragment.class.getSimpleName();
    private FragmentPageAdapter frpage;
    ViewPager mViewPager;
    SlidingTabLayout slidingTabLayout;

    public static TabbedFragment newInstance() {
        return new TabbedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tabbed, container, false);
        frpage = new FragmentPageAdapter(getChildFragmentManager());


        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mViewPager.setAdapter(frpage);

        /*slidingTabLayout = (SlidingTabLayout) inflater.inflate(R.id.sliding_tabs, container , false);
		slidingTabLayout.setDistributeEvenly(true);
		slidingTabLayout.setViewPager(mViewPager);*/

        return v;
    }


}
