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
    //student type
    public static final String STUDENT_TYPE = "student";
    //company type
    public static final String COMPANY_TYPE = "company";
    //type attribute of App_User class
    public static final String TYPE = "userType";
    //name attribute for both student and company
    public static final String NAME ="name" ;
    //boolean for checking if profile is completed
    public static final String IS_PROFILE_COMPLETED = "isProfileCompleted";
    //surname attribute student class
    public static final String SURNAME ="surname" ;
    //birthdate attribute for student
    public static final String BIRTH_DATE ="birthDate" ;
    //photo for both classes
    public static final String PROFILE_PHOTO ="profilePhoto" ;
    //phone number
    public static final String PHONE_NUMBER ="phoneNumber" ;
    //education for student
    public static final String EDUCATION ="education" ;
    //skills for student
    public static final String SKILLS ="skills" ;
    //address for both classes
    public static final String ADDRESS ="address" ;
    //cv for student
    public static final String CV ="cv" ;
    //filed for company
    public static final String FIELD ="field" ;
    //detail for company
    public static final String DETAIL ="detail" ;
    //gender for student
    public static final String GENDER ="gender" ;
    //language skills
    public static final String LANGUAGE_SKILLS = "lanuageSkills";
    //job offer list
    public static final String JOB_OFFERS = "jobOffers";



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
