package it.polito.mobile.polijobplacement.Search;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import it.polito.mobile.polijobplacement.Data.App_User;
import it.polito.mobile.polijobplacement.Data.JobApplication;
import it.polito.mobile.polijobplacement.Data.Messages;
import it.polito.mobile.polijobplacement.Data.Student;
import it.polito.mobile.polijobplacement.R;
import static it.polito.mobile.polijobplacement.Search.Fragment_Search_Company.STUDENT;

public class Detail_Student extends ActionBarActivity {
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__student);
        username = getIntent().getStringExtra(STUDENT);
        JobApplication data = (JobApplication)getApplicationContext();
        final Student student = (Student)data.getStudent(username);
        TextView name = (TextView)findViewById(R.id.name);
        TextView surname = (TextView)findViewById(R.id.surname);
        TextView gender = (TextView)findViewById(R.id.gender);
        TextView address = (TextView)findViewById(R.id.address);
        TextView birth_date = (TextView)findViewById(R.id.birth_date);
        if(student == null) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
        else{
            name.setText("Name:  " + student.getName().toString());
            surname.setText("Surname:  " + student.getSurname().toString());
            gender.setText("Gender:  " + student.getGender());
            address.setText("Address:  " + student.getAddress());
            birth_date.setText("Birthdate:  " + student.getBirth_date());
        }
        Button contact = (Button)findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Messages message = new Messages();
                message.setFromUser(((JobApplication) getApplicationContext()).getUser());
                App_User touser = ((JobApplication) getApplicationContext()).getAppUser(student.getUserName());
                message.setToUser(touser);
                message.setTitle("Confirmation");
                message.saveInBackground();
                Toast.makeText(getApplicationContext(), "Your message sent!", Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail__student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
