package it.polito.mobile.polijobplacement.Data;



import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import it.polito.mobile.polijobplacement.Data.App_User;
import it.polito.mobile.polijobplacement.Data.Student;
import it.polito.mobile.polijobplacement.Data.Company;


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
    public static final String LOCATION = "location";


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
    public App_User getUser(){
        ParseUser cUser = ParseUser.getCurrentUser();
        String username = cUser.getUsername();
        ParseQuery<App_User> query = ParseQuery.getQuery(App_User.class);
        query.whereEqualTo("username", username);
        List<App_User> result = null;
        try {
            result = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result.get(0);

    }
    public List<Company> Company_List() {
        return null;
    }

    public List<Student> Student_List() {
        ParseQuery<Student> list_student = ParseQuery.getQuery(Student.class);



        List<Student> result = null;
        try {
            result = list_student.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

   /* public List<Jobs_by_Type> getJobsbyType() {
        return null;
    }
*/
    public List<JobOffers> getJobList(String title, String company_name, String location, String type) throws ParseException {
        boolean f1 = false;
        boolean f2 = false;
        boolean f3 = false;
        boolean f4 = false;
        ParseQuery<JobOffers> query1 = ParseQuery.getQuery(JobOffers.class);
        if(!title.isEmpty()) {
            query1.whereEqualTo(JobApplication.JOB_TITLE, title);
            f1 = true;
        }
        ParseQuery<JobOffers> query2 = ParseQuery.getQuery(JobOffers.class);
        if( !company_name.isEmpty()){
            Company company = getCompany_byName(company_name);
            query2.whereEqualTo(JobApplication.JOB_OFFERS_OFFERED_BY, company);
            f2 = true;
        }
        ParseQuery<JobOffers> query3 = ParseQuery.getQuery(JobOffers.class);
        if( !location.isEmpty()){
            query3.whereEqualTo(JobApplication.LOCATION, location);
            f3 = true;
        }
        ParseQuery<JobOffers> query4 = ParseQuery.getQuery(JobOffers.class);
        if( !type.equalsIgnoreCase("Any Category")){
            query4.whereEqualTo(JobApplication.TYPE, type);
            f4 = true;
        }
        List<ParseQuery<JobOffers>> queries = new ArrayList<ParseQuery<JobOffers>>();
        if( f1 ) {
            queries.add(query1);
        }
        if( f2 ) {
            queries.add(query2);
        }
        if(f3 )
        {
            queries.add(query3);
        }
        if(f4 ) {
            queries.add(query4);
        }
        ParseQuery<JobOffers> mainQuery = ParseQuery.or(queries);
        List<JobOffers> list = null;
        try {
            list = mainQuery.find();

        } catch (ParseException e) {
            e.printStackTrace();

        }
        return list;
    }

    private Company getCompany_byName(String company_name) throws ParseException {
        ParseQuery<Company> query = ParseQuery.getQuery(Company.class);
        query.whereEqualTo(JobApplication.NAME, company_name);
        return query.getFirst();
    }

    public static Student getStudent(String username) {

        ParseQuery<Student> query = ParseQuery.getQuery(Student.class);
        query.whereEqualTo("username", username);
        List<Student> result = null;
        Student s = null;
        try {
            result = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(result.size() == 0){
            return s;
        }
        return result.get(0);
    }
    public static Company getCompany(String username) {

        ParseQuery<Company> query = ParseQuery.getQuery(Company.class);
        query.whereEqualTo("username", username);
        List<Company> result = null;
        Company s = null;
        try {
            result = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(result.size() == 0){
            return s;
        }
        return result.get(0);
    }
    public List<Jobs_by_Type> getJobsbyType() {


        ParseQuery<Jobs_by_Type> query = ParseQuery.getQuery(Jobs_by_Type.class);
        List<Jobs_by_Type> list = null;
        try {
            list = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void add_jobs_by_type(JobOffers offer) {

        ParseQuery<Jobs_by_Type> query = ParseQuery.getQuery(Jobs_by_Type.class);
        query.whereEqualTo("type", offer.getCategory());
        List<Jobs_by_Type> jbt = null;
        try {
            jbt = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if( jbt != null){
            jbt.get(0).add(offer);
            jbt.get(0).saveEventually();
        }
        if( jbt == null){
            Jobs_by_Type job = new Jobs_by_Type();
            job.setTYPE(offer.getCategory());
            job.add(offer);
            job.saveInBackground();
        }
    }

    public App_User getAppUser(String userName) {

        ParseQuery<App_User> query = ParseQuery.getQuery(App_User.class);
        query.whereEqualTo("username", userName);
        List<App_User> result = null;
        App_User s = null;
        try {
            result = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(result.size() == 0){
            return s;
        }
        return result.get(0);
    }
}
