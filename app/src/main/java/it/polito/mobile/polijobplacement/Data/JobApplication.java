package it.polito.mobile.polijobplacement.Data;



import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

import it.polito.mobile.polijobplacement.Data.App_User;
import it.polito.mobile.polijobplacement.Data.Company;
import it.polito.mobile.polijobplacement.Data.Student;

/**
 * Created by shahboz on 26/04/2015.
 */
public class JobApplication extends android.app.Application{

    public static final String STUDENT_TYPE = "student";
    public static final String COMPANY_TYPE = "company";
    public static final String TYPE = "userType";
    private App_User currentUser = null;
    public JobApplication(){

    }
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(App_User.class);
        ParseObject.registerSubclass(Student.class);
        ParseObject.registerSubclass(Company.class);
        Parse.initialize(this, "7UUCWXAEvaHoabQcvBBkTWS3AxuDJdx1KwO7DyeW", "nlKEMOiOxYwrOrmN9rf7r5CNYzPDIM4XTRXOA7GF");
        ParseUser.enableAutomaticUser();
        ParseUser.getCurrentUser().saveInBackground();

    }
   public App_User getUser(){ return currentUser;}
    public List<App_User> Company_List() {
        return null;
    }

    public List<App_User> Student_List() {
        return null;
    }
}
