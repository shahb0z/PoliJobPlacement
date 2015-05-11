package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;

/**
 * Created by user on 5/12/2015.
 */
@ParseClassName("Languages")
public class Languages extends  {
    private String name;
    private String level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }



}
