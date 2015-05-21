package it.polito.mobile.polijobplacement.Profile;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shahboz on 10/05/2015.
 */
public class PageList {


    private List<Fragment> list;
    public PageList(){
        list = new ArrayList<>();
        list.add(AnagraficDataFragment.create());
        list.add(AddressDataFragment.newInstance());
        list.add(ItemFragment.newInstance());
        //list.add(ReviewFragment.newInstance());

    }

    public Fragment createFragment(int position){
        Fragment f = new Fragment();
        switch (position){
            case 0:
                return list.get(0);
            case 1:
                return list.get(1);
            case 2:
                return list.get(2);
            /*case 3:
                return list.get(3);*/
        }
        return f;
    }
    public List<Fragment> getList() {
        return list;
    }

    public boolean isComplete(int currentItem) {
        switch (currentItem) {
            case 0:
                return ((AnagraficDataFragment)list.get(0)).isCompleted();
            case 1:
                return ((AddressDataFragment)list.get(1)).isCompleted();
            case 2:
                return ((ItemFragment)list.get(2)).isCompleted();
            /*case 3:
                return true;*/
        }
        return false;
    }
}
