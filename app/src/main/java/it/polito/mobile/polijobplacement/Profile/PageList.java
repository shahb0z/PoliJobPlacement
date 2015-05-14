package it.polito.mobile.polijobplacement.Profile;

import android.app.Fragment;

/**
 * Created by shahboz on 10/05/2015.
 */
public class PageList {
    public PageList(){

    }

    public Fragment createFragment(int position){
        Fragment f = new Fragment();
        f = AnagraficDataFragment.create();
        /*switch (position){
            case 0:
                f = AnagraficDataFragment.create();
            case 1:

        }*/
        return f;
    }
}
