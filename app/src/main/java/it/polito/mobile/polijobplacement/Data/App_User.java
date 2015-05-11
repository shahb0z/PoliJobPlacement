package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by user on 5/7/2015.
 */
@ParseClassName("App_User")
public class App_User extends ParseUser {

    public static final String TYPE = "type";
    private static final String NAME ="name" ;
    public static final String STUDENT_TYPE = "student";
    public static final String COMPANY_TYPE ="company" ;





    public String getType() {
        return getString(TYPE);
    }




    public void setType(String type) {
        this.put(TYPE, type);
    }



    public void setName(String name) {
        this.put(NAME, name);
    }
    public String getName(){ return getString(NAME);}
}
