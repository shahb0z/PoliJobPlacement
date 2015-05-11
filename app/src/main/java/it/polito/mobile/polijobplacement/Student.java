package it.polito.mobile.polijobplacement;

import com.parse.ParseClassName;

import java.util.Date;

/**
 * Created by user on 5/7/2015.
 */
@ParseClassName("Student")
public class Student extends App_User {
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String GENDER = "gender";
    private static  Date BIRTHDATE;
    //degrees, languages;

    public Student(){
        super();
    }
    public  String getName() {
        return getString(NAME);
    }

    public  String getSurname() {
        return getString(SURNAME);
    }

    public  String getGender() {
        return getString(GENDER);
    }


}
