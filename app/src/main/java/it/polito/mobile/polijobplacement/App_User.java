package it.polito.mobile.polijobplacement;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by user on 5/7/2015.
 */
@ParseClassName("App_User")
public class App_User extends ParseObject {
    public static final String mail = "mail";
    private static final String password = "password";
    public static final String TYPE = "type";
    private static final String NAME ="name" ;
    public static final String STUDENT_TYPE = "student";
    public static final String COMPANY_TYPE ="company" ;


    public String getPassword() {
        return getString(password);
    }



    public String getType() {
        return getString(TYPE);
    }


    public void setMail(String m) {
        this.put(mail, m);
    }

    public void setPassword(String p) {
        this.put(password, p);
    }

    public void setType(String type) {
        this.put(TYPE, type);
    }

    public String getMail() {
        return getString(mail);
    }

    public void setName(String name) {
        this.put(NAME, name);
    }
    public String getName(){ return getString(NAME);}
}
