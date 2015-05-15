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
    //message list for both class
    public static final String MESSAGES = "message";
    //list of jobs for student class
    public static final String JOB_APPLIED ="jobApplied" ;

    //list of liked or saved jobs for student class
    public static final String JOB_SAVED = "jobSaved";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String KEYWORD_TYPE = "keywordType";
    //keyword types us them along application
    public static final String KEYWORD_SKILL_TYPE = "skillType";
    public static final String KEYWORD_JOB_NAME_TYPE = "jobName";
    public static final String KEYWORD_JOB_CATEGORY_TYPE = "jobCategory";

    //employment types us them along application
    /************************************************************/
    public static final String EMPLOYMENT_FULL_TIME = "Full Time";
    public static final String EMPLOYMENT_PART_TIME = "Part Time";
    public static final String EMPLOYMENT_CONTRACTOR = "Contractor";
    public static final String EMPLOYMENT_INTERN = "Intern";
    public static final String EMPLOYMENT_SEASONAL = "Seasonal";
    /***********************************************************/
    public static final String EMPLOYMENT_TYPE = "employmentType";
    public static final String CATEGORY = "category";
    public static final String PUBLISH_DATE = "publishDate";
    public static final String JOB_TITLE = "jobTitle";
    public static final String JOB_DESCRIPTION = "jobDescription";
    public static final String LANGUAGE_NAME = "languageName";
    public static final String LANGUAGE_LEVEL = "languageLevel";
    public static final String UNIVERSITY = "university";
    public static final String DEGREE_END_YEAR = "endYear";
    public static final String DEGREE_START_YEAR = "startYear";
    public static final String DEGREE_MAJOR = "major";
    public static final String DEGREE_TYPE = "type";
    public static final String MESSAGE_FROM = "from";
    public static final String MESSAGE_TO = "to";
    public static final String MESSAGE_TITLE = "title";
    public static final String MESSAGE_CONTENT = "content";
    public static final String JOB_OFFERS_APPLICANT_LIST = "applicantList";
    public static final String JOB_OFFERS_OFFERED_BY = "offeredBy";
    public static final String JOB_OFFERS_SALARY = "salary";
    public static final String JOB_OFFERS_DUE_DATE = "dueDate";
    public static final String MESSAGE_READ = "isRead";
    public static final String JOB_OFFERS_SKILLS_LIST = "skillsList";
    public static final String JOB_OFFERS_LANGUAGE = "language";


    private App_User currentUser = null;
    public JobApplication(){

    }
    @Override
    public void onCreate() {
        super.onCreate();
        ParseUser.registerSubclass(App_User.class);
        ParseUser.registerSubclass(Student.class);
        ParseUser.registerSubclass(Company.class);
        ParseObject.registerSubclass(JobOffers.class);
        ParseObject.registerSubclass(Messages.class);
        ParseObject.registerSubclass(Languages.class);
        ParseObject.registerSubclass(Degree.class);
        ParseObject.registerSubclass(Keyword.class);
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
