package it.polito.mobile.polijobplacement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.SignUpCallback;

import it.polito.mobile.polijobplacement.Data.App_User;
import it.polito.mobile.polijobplacement.Data.Company;
import it.polito.mobile.polijobplacement.Data.JobApplication;
import it.polito.mobile.polijobplacement.Data.Student;

/**
 * @author shahb0z
 * The activity is responsible for sign up flow
 */

public class SignupActivity1 extends ActionBarActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordAgainEditText;
    private String userType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        usernameEditText = (EditText)findViewById(R.id.username_edit_text);
        passwordEditText = (EditText)findViewById(R.id.password_edit_text);
        passwordAgainEditText = (EditText)findViewById(R.id.password_again_edit_text);
        passwordAgainEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.edit_text_action_signup ||
                        actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    signup();
                    return true;
                }
                return false;
            }
        });

        Button mActionButton = (Button) findViewById(R.id.signup_confirm_button);
        mActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                signup();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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

    /**
     * The method for handling sign up validation and sign up with <a href="http://parse.com">parse.com</a> .
     */
    private void signup() {

        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String passwordAgain = passwordAgainEditText.getText().toString().trim();

        // Validate the sign up data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));

        if(username.length() == 0){
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_username));
        }

        if(!isEmailValid(username)){
            if(validationError){
                validationErrorMessage.append(getString(R.string.error_email_not_valid));
            }else{
                validationError = true;
                validationErrorMessage.append(getString(R.string.error_email_not_valid));
            }
        }
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }else{
                validationError = true;
                validationErrorMessage.append(getString(R.string.error_blank_password));
            }

        }
        if (!password.equals(passwordAgain)) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }else{
                validationError = true;
                validationErrorMessage.append(getString(R.string.error_mismatched_passwords));
            }

        }
        if(userType == null){
            if(validationError){
                validationErrorMessage.append(getString(R.string.error_user_type));
            }else{
                validationError=true;
                validationErrorMessage.append(getString(R.string.error_user_type));
            }
        }
        validationErrorMessage.append(getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(SignupActivity1.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(SignupActivity1.this);
        dialog.setMessage(getString(R.string.progress_signup));
        dialog.show();
        App_User user;
        if(userType.equals(JobApplication.STUDENT_TYPE)){
            user = new Student();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(username);
            user.setType(userType);
        }
        else{
            user = new Company();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(username);
            user.setType(userType);
        }

        // Set up a new Parse user

        // Call the Parse signup method
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(SignupActivity1.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(SignupActivity1.this, MainActivity1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
    /**
     * method for getting user input for user type
     */
    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox)view).isChecked();
        CheckBox student = (CheckBox)findViewById(R.id.checkbox_student);
        CheckBox company = (CheckBox)findViewById(R.id.checkbox_company);
        if(checked){
            switch(view.getId()){
                case R.id.checkbox_student:
                    student.setChecked(true);
                    company.setChecked(false);
                    userType = JobApplication.STUDENT_TYPE;
                    break;
                case R.id.checkbox_company:
                    company.setChecked(true);
                    student.setChecked(false);
                    userType = JobApplication.COMPANY_TYPE;
                    break;
            }
        }else{
            userType = null;
        }

    }
    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }
}
