package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by shahboz on 13/05/2015.
 *
 * the class is used to save keywords
 * basically it has following attributes
 * --type, e.g. skillType, jobType
 * --name, e.g. Software Engineering
 */
@ParseClassName("Keyword")
public class Keyword extends ParseObject {
    public Keyword(){

    }
    public String getType(){

        return getString(JobApplication.KEYWORD_TYPE);
    }
    public void setType(String s){
        put(JobApplication.KEYWORD_TYPE,s);
    }
    public void setName(String name) {
        this.put(JobApplication.NAME, name);
    }

    public String getName(){ return getString(JobApplication.NAME);}
}
