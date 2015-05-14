package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by user on 5/12/2015.
 */
@ParseClassName("Languages")
public class Languages extends ParseObject{
    private String name;
    private String level;

    public String getName() {
        return getString(JobApplication.LANGUAGE_NAME);
    }

    public void setName(String name) {
        put(JobApplication.LANGUAGE_NAME,name);
    }

    public String getLevel() {
        return getString(JobApplication.LANGUAGE_LEVEL);
    }

    public void setLevel(String level) {
        put(JobApplication.LANGUAGE_LEVEL,level);
    }



}
