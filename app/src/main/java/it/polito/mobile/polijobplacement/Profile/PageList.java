package it.polito.mobile.polijobplacement.Profile;

import android.support.v4.app.Fragment;

/**
 * Created by shahboz on 10/05/2015.
 */
public class PageList {
    public PageList(){

    }

    public Fragment createFragment(int position){
        Fragment f = new Fragment();

        switch (position){
            case 0:
                f = AnagraficDataFragment.create();
                break;
            case 1:
                f = AddressDataFragment.newInstance();
                break;
            case 2:
                f = ItemFragment.newInstance();
                break;
        }
        return f;
    }
}
