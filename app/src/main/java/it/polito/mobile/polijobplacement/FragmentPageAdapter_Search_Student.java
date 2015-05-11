package it.polito.mobile.polijobplacement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.List;
import java.util.Locale;

/**
 * Created by user on 5/9/2015.
 */
public class FragmentPageAdapter_Search_Student extends FragmentPagerAdapter {
    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;

    final int PAGE_COUNT = 2;
    private String  ku[]= {"STUDENT LIST","STUDENT IN SPECIFIC FILED"};
    private List<Fragment> fragments;

    private Bundle searchResults=null;
    public FragmentPageAdapter_Search_Student(FragmentManager fm) {
        super(fm);
        this.mFragmentManager = fm;
    }

    /* @Override
     public Fragment getItem(int position) {
         Fragment fragment = new TabbedContentFragment();
         Bundle args = new Bundle();
         args.putInt(TabbedContentFragment.ARG_SECTION_NUMBER, position + 1);
         fragment.setArguments(args);
         return fragment;
     }*/





    private Context context;

    /*public FragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
       this.mFragmentManager = fm;
        this.context = context;

    }
*/
    public void setSearchResults(Bundle results) {
        this.searchResults=results;
    }

    public Bundle getSearchResults() {
        return searchResults;
    }



    @Override
    public int getCount() {
        return 2;

    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        switch (position) {
            case 0:{
                f =  new Fragment_Search_Company();
                return  f;
            }
            case 1:{
                f = new Fragment_Search_Company_Specific();
                return  f;
            }

        }
        return f;

    }



    /*@Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

    	String tabTitles[]  =context.getResources().getStringArray(R.array.Tab_array);
        return tabTitles[position];

    }*/
    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();

        switch (position) {
            case 0:

                return ku[0];
            case 1:
                return ku[1];
        }
        return null;
    }

}
