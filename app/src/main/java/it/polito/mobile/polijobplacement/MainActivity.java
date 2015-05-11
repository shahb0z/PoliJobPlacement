package it.polito.mobile.polijobplacement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Determine whether the current user is anonymous
            Database db = (Database) getApplicationContext();
            setContentView(R.layout.activity_main1);
          if(db.getUser() == null) {
              BlankFragment welcomeFragment = new BlankFragment();
              getFragmentManager().beginTransaction()
                      .add(R.id.fragment_container, welcomeFragment).commit();
          }
         else{
              if(db.getUser().TYPE.equalsIgnoreCase("Student")){
                  startActivity(new Intent(getApplicationContext(), StudentMainPageActivity.class));
              }
              else
                  startActivity(new Intent(getApplicationContext(), Company_Main_Page.class));
          }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_home, menu);
        return true;
    }
}
