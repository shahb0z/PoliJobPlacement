package it.polito.mobile.polijobplacement.Search;

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
public class FragmentPageAdapter_Search extends FragmentPagerAdapter {
    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;

    final static int PAGE_COUNT = 2;
    private String  page[]= {"Jobs By Categories","Search By Filters"};
    private List<Fragment> fragments;

    private Bundle searchResults=null;
    public FragmentPageAdapter_Search(FragmentManager fm) {
        super(fm);
        this.mFragmentManager = fm;
    }







    public void setSearchResults(Bundle results) {
        this.searchResults=results;
    }

    public Bundle getSearchResults() {
        return searchResults;
    }



    @Override
    public int getCount() {
        return PAGE_COUNT;

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
                f =  Search_List.newInstance();
                return  f;
            }
            case 1:{
                f = Search_by_filters.newInstance();
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


        switch (position) {
            case 0:

                return page[0];
            case 1:
                return page[1];
        }
        return null;
    }

}
