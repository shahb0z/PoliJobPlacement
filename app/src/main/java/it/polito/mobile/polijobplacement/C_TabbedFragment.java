package it.polito.mobile.polijobplacement;

/**
 * Created by Admin on 5/13/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class C_TabbedFragment  extends  Fragment{
    public static final String TAG = C_TabbedFragment.class.getSimpleName();
    private Company_FragmentPageAdapter frpage;
    ViewPager mViewPager;
    SlidingTabLayout slidingTabLayout;

    public static  C_TabbedFragment newInstance() {
        return new  C_TabbedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragemnt_tabbed_company, container, false);
        frpage=  new Company_FragmentPageAdapter(getChildFragmentManager()) ;


        mViewPager = (ViewPager) v.findViewById(R.id.pager_company);
        mViewPager.setAdapter(frpage);

        /*slidingTabLayout = (SlidingTabLayout) inflater.inflate(R.id.sliding_tabs, container , false);
		slidingTabLayout.setDistributeEvenly(true);
		slidingTabLayout.setViewPager(mViewPager);*/

        return v;
    }

}
