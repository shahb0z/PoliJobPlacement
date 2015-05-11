package it.polito.mobile.polijobplacement;

/**
 * Created by user on 5/7/2015.
 */


        import android.app.Application;

        import com.parse.Parse;
        import com.parse.ParseException;
        import com.parse.ParseObject;
        import com.parse.ParseQuery;

        import java.util.List;


public class Database extends Application {

    private App_User User = null;

    @Override
    public void onCreate() {
        super.onCreate();

        //User = null;


        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(App_User.class);
        ParseObject.registerSubclass(Student.class);
        //ParseObject.registerSubclass(Company.class);

        Parse.initialize(this, "gKAAe6a0RPPgYqGqGScFIEClRWOCdFRPa31xWCVM", "a8HVSsaj4NDFHgZiyU2qa6DTYk3LmbVsznu0zx1p");



    }




    public boolean  login(String mail, String password) {

        ParseQuery<App_User> userQuery = ParseQuery.getQuery(App_User.class);
        userQuery.whereEqualTo(App_User.mail,mail);
        List<App_User> result = null;
        try {
            result = userQuery.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(result.size() == 1) {
            App_User user = result.get(0);

            if (user.getPassword().equals(password)) {
                User = user;
                return true;
            } else {


                return false;
            }
        }


       return false;
    }
    public List<App_User>  Company_List() {

        ParseQuery<App_User> userQuery = ParseQuery.getQuery(App_User.class);
        userQuery.whereEqualTo(App_User.TYPE,"company");
        List<App_User> result = null;
        try {
            result = userQuery.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }



    public boolean registerNewAccount(App_User newUser) throws ParseException {

        /* 1 - verify we don't have other users with same mail */

        ParseQuery<App_User> userQuery = ParseQuery.getQuery(App_User.class);
        userQuery.whereEqualTo(App_User.mail,newUser.getMail());



            List<App_User> result = userQuery.find();

            if(result.size()>0){

                //Log.println(Log.ASSERT,"GLOBAL DATA", "this account already exists");
                return false;
            }
            else {

                /* 2 - can proceed with registration */


                    newUser.saveInBackground();
                    User = newUser;

                    return true;


            }




    }


    public App_User getUser() {
        return User;
    }




    public List<App_User> Student_List() {


            ParseQuery<App_User> userQuery = ParseQuery.getQuery(App_User.class);
            userQuery.whereEqualTo(App_User.TYPE,"student");
            List<App_User> result = null;
            try {
                result = userQuery.find();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return result;

    }
}

