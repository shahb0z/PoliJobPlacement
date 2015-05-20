package it.polito.mobile.polijobplacement.Data;

import com.parse.GetDataCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.File;
import java.util.List;

/**
 * Created by user on 5/7/2015.
 */
@ParseClassName("_User")
public class App_User extends ParseUser {

    public App_User(){

    }

    public boolean isProfileCompleted(){
        return getBoolean(JobApplication.IS_PROFILE_COMPLETED);
    }

    public void setProfileCompleted(){
        this.put(JobApplication.IS_PROFILE_COMPLETED,"true");
    }

    public String getType() {
        return getString(JobApplication.TYPE);
    }

    public void setType(String type) {
        this.put(JobApplication.TYPE, type);
    }

    public static App_User getCurrentUser()
    {
        ParseQuery<App_User> pq=  ParseQuery.getQuery(App_User.class);
        try {
            return pq.get(ParseUser.getCurrentUser().getObjectId());
        }
        catch (Exception ex)
        {
            return null;
        }
    }

}
