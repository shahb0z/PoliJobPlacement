package it.polito.mobile.polijobplacement.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.polito.mobile.polijobplacement.Data.Degree;
import it.polito.mobile.polijobplacement.Data.JobApplication;
import it.polito.mobile.polijobplacement.Data.JobOffers;
import it.polito.mobile.polijobplacement.Data.Languages;
import it.polito.mobile.polijobplacement.Data.Messages;
import it.polito.mobile.polijobplacement.Data.Student;
import it.polito.mobile.polijobplacement.MainActivity1;
import it.polito.mobile.polijobplacement.R;


public class StudentProfile extends FragmentActivity{
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private CustomViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    /**
     * For taking actions
     *
     */
    private Button mNextButton;
    private Button mPrevButton;

    /**
     *
     *
     * boolean for detecting review
     */

    private boolean mEditAfterReview;

    /**
     * strip along upper side of page
     */

    private StepPagerStrip mStepPagerStrip;
    private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-M-yyyy");


    /**
     * represents list of fragments necessary for pager
     */

    private PageList mFragmentList = new PageList();

    private String name;
    private String surname;
    private String email;
    private String gender;
    private String country;
    private String region;
    private String nationality;
    private Date birthdate;
    private List<Degree> degrees;
    private List<String> skills;
    private List<Languages> languages;

//
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (CustomViewPager) findViewById(R.id.pager_student);
        mPager.setPagingEnabled(false);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mStepPagerStrip.setCurrentPage(position);
                mEditAfterReview = false;
                updateBottomBar();
            }
        });

        mStepPagerStrip = (StepPagerStrip)findViewById(R.id.strip);

        mNextButton = (Button) findViewById(R.id.next_button);
        mPrevButton = (Button) findViewById(R.id.prev_button);


        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mFragmentList.isComplete(mPager.getCurrentItem())){
                    if(mPager.getCurrentItem() == NUM_PAGES ){
                        saveStudentData();
                    }else
                        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                }
                else{
                    Toast.makeText(StudentProfile.this, "Add mandatory items!!!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
        });
        mStepPagerStrip.setPageCount(NUM_PAGES + 1);
        updateBottomBar();
    }

    private void saveStudentData() {
        name = (((AnagraficDataFragment)mFragmentList.getList().get(0)).getName().getText().toString());
        surname = (((AnagraficDataFragment)mFragmentList.getList().get(0)).getSurname().getText().toString());
        email = (((AnagraficDataFragment)mFragmentList.getList().get(0)).getEmail().getText().toString());
        gender = (((AnagraficDataFragment)mFragmentList.getList().get(0)).getGender().getSelectedItem().toString());
        try {
            birthdate = DATE_FORMATTER.parse((((AnagraficDataFragment) mFragmentList.getList().get(0)).getmBirthDateTextView().getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        country = (((AddressDataFragment)mFragmentList.getList().get(1)).getCountry().getText().toString());
        region = (((AddressDataFragment) mFragmentList.getList().get(1)).getRegion().getText().toString());
        nationality = (((AddressDataFragment)mFragmentList.getList().get(1)).getNation().getText().toString());
        degrees = ((ItemFragment)mFragmentList.getList().get(2)).getDegreeList();
        skills = ((ItemFragment)mFragmentList.getList().get(2)).getSkillList();
        languages = ((ItemFragment)mFragmentList.getList().get(2)).getLanguagesList();
        Student student = JobApplication.getStudent(ParseUser.getCurrentUser().getUsername());
        if(student == null)
            Toast.makeText(StudentProfile.this, "Check your internet connection!", Toast.LENGTH_LONG)
                    .show();
        else{
            final ProgressDialog dialog = new ProgressDialog(StudentProfile.this);
            dialog.setMessage(getString(R.string.progress_student_save));
            dialog.show();
            student.setName(name);
            student.setSurname(surname);
            student.setUserName(email);
            //user credentials
            ParseUser.getCurrentUser().setEmail(email);
            ParseUser.getCurrentUser().setUsername(email);
            //
            student.setBirth_date(birthdate);
            student.setGender(gender);
            student.setCountry(country);
            student.setCity(region);
            student.setNationality(nationality);
            student.setEducation(degrees);
            student.setSkills(skills);
            student.setLanguage_skills(languages);
            student.setJobApplied(new ArrayList<JobOffers>());
            student.setJobSaved(new ArrayList<JobOffers>());
            student.setMessages(new ArrayList<Messages>());

            student.saveInBackground(new SaveCallback() {
                @Override
                public void done(com.parse.ParseException e) {
                    dialog.dismiss();
                    if (e != null) {
                        // Show the error message
                        Toast.makeText(StudentProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        // Start an intent for the dispatch activity
                        Intent intent = new Intent(StudentProfile.this, MainActivity1.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            });

        }
    }

    /**
     * The method responsible for updating button view
     */
    private void updateBottomBar() {
        int position = mPager.getCurrentItem();
        if (position == NUM_PAGES) {
            mNextButton.setText(R.string.action_finish);
            mNextButton.setBackgroundResource(R.drawable.finish_background);

        }else{
            mNextButton.setText(R.string.action_next);
            mNextButton.setBackgroundResource(R.drawable.selectable_item_background);
        }
        mPrevButton.setVisibility(position <= 0 ? View.INVISIBLE : View.VISIBLE);
    }
    public PageList getmFragmentList() {
        return mFragmentList;
    }




    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.createFragment(position);
        }

        @Override
        public int getCount() {

            return NUM_PAGES+1;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {


        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }
    }

}
