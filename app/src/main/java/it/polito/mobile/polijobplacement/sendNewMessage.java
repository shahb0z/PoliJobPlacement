package it.polito.mobile.polijobplacement;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

import it.polito.mobile.polijobplacement.Data.App_User;
import it.polito.mobile.polijobplacement.Data.Messages;

public class sendNewMessage extends ActionBarActivity {
    Button send;
    EditText des;
    EditText detail;
    EditText title;
    Messages messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_new_message);
        send=(Button)findViewById(R.id.btnSend);
        detail=(EditText)findViewById(R.id.txtDetails);
        des=(EditText)findViewById(R.id.txtDes);
        title=(EditText)findViewById(R.id.txtSubject);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messages =new Messages();
                messages.setTitle(title.getText().toString());
                messages.setContent(detail.getText().toString());
                App_User app_user= App_User.getCurrentUser();
                messages.setFromUser(app_user);
                ParseQuery<App_User> pq=  ParseQuery.getQuery(App_User.class);
                pq= pq.whereEqualTo("email",des.getText().toString());
                pq.findInBackground(new FindCallback<App_User>() {
                    @Override
                    public void done(List<App_User> app_users, ParseException e) {
                        if (e==null)
                        {
                            messages.setToUser(app_users.get(0));

                                messages.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if(e!=null)
                                        {
                                            Toast.makeText(sendNewMessage.this,e.getMessage(),Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(sendNewMessage.this,"sucess",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                        }
                        else
                        {
                            Toast.makeText(sendNewMessage.this,"Wrong destination address",Toast.LENGTH_LONG).show();
                        }
                    }
                });


                //pq.findInBackground(List,);


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_new_message, menu);
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
