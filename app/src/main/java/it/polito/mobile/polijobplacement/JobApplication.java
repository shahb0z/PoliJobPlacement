package it.polito.mobile.polijobplacement;



import com.parse.Parse;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by shahboz on 26/04/2015.
 */
public class JobApplication extends android.app.Application{

    public static final String STUDENT_TYPE = "student";
    public static final String COMPANY_TYPE = "company";
    public static final String TYPE = "userType";
    public JobApplication(){

    }
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "7UUCWXAEvaHoabQcvBBkTWS3AxuDJdx1KwO7DyeW", "nlKEMOiOxYwrOrmN9rf7r5CNYzPDIM4XTRXOA7GF");
        ParseUser.enableAutomaticUser();
        ParseUser.getCurrentUser().saveInBackground();
    }

    public List<App_User> Company_List() {
        return null;
    }

    public List<App_User> Student_List() {
        return null;
    }
}
