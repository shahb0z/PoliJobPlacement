package it.polito.mobile.polijobplacement;

/**
 * Created by Admin on 5/13/2015.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.List;
import java.util.Locale;
public class Company_FragmentPageAdapter extends  FragmentPagerAdapter  {

    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;

    final int PAGE_COUNT = 4;
    private String  ku1[]= {"Home","Jobs","Applied Job","InBox"};
    private List<Fragment> fragments;

    private Bundle searchResults=null;

    public Company_FragmentPageAdapter (FragmentManager fm) {
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

    /*public Student_FragmentPageAdapter(FragmentManager fm, Context context) {
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
        return PAGE_COUNT;

    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:{
                Fragment f= it.polito.mobile.polijobplacement.Compay_List_ViewFragment.newInstance("Company_2", "", "", searchResults);
                return  f;
            }
            case 1:{
                Fragment f= it.polito.mobile.polijobplacement.Compay_List_ViewFragment.newInstance("Company_2", "", "", searchResults);
                return  f;
            }
            case 2:{
                Fragment f= it.polito.mobile.polijobplacement.Compay_List_ViewFragment.newInstance("Company_2", "", "", searchResults);
                return f;}
            case 3:
                inboxFragment f= inboxFragment.newInstance("", "", "", searchResults);
                return  f;


            default:
                break;
        }
        return null;

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

                return ku1[0];
            case 1:
                return ku1[1];
            case 2:
                return ku1[2];
            case 3:
                return ku1[3];
        }
        return null;
    }

}
