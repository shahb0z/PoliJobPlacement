package it.polito.mobile.polijobplacement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import it.polito.mobile.polijobplacement.Data.App_User;
import it.polito.mobile.polijobplacement.Data.Company;
import it.polito.mobile.polijobplacement.Data.JobApplication;
import it.polito.mobile.polijobplacement.Data.Student;


public class MainActivity1 extends Activity {
    public static final String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Determine whether the current user is anonymous

        if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
        //if the user is anonymous send the user to welcome fragment
            setContentView(R.layout.activity_main1);
            //
            if(findViewById(R.id.fragment_container) != null){
                if(savedInstanceState != null){
                    return;
                }
                BlankFragment welcomeFragment = new BlankFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.fragment_container,welcomeFragment).commit();
            }

        }
        else{
            ParseUser currentUser = ParseUser.getCurrentUser();
           if(currentUser != null){
               //if the current user is logged in send the user to main page
               //send the user to student main page
               if(((App_User)currentUser).getType().equals(JobApplication.STUDENT_TYPE)){

                   startActivity(new Intent(this,StudentMainPageActivity.class));
               }

               //send the user to company page
               else if(((App_User)currentUser).getType().equals(JobApplication.COMPANY_TYPE))

                   startActivity(new Intent(this,CompanyMainPageActivity.class));
            }

            else{
               //if the user not logged in send the user to login activity
                startActivity(new Intent(this,LoginActivity1.class));
            }
        }

    }
}
