package it.polito.mobile.polijobplacement.Profile;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import it.polito.mobile.polijobplacement.R;


public class StudentProfile extends FragmentActivity implements DatePickerDialog.OnDateSetListener{
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

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
    /**
     * represents list of fragments necessary for pager
     */

    private PageList mFragmentList = new PageList();

    private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-M-yyyy");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
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
        mStepPagerStrip.setOnPageSelectedListener(new StepPagerStrip.OnPageSelectedListener() {
            @Override
            public void onPageStripSelected(int position) {
                position = Math.min(mPagerAdapter.getCount() - 1, position);
                if (mPager.getCurrentItem() != position) {
                    mPager.setCurrentItem(position);
                }
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mPrevButton = (Button) findViewById(R.id.prev_button);


        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
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

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        AnagraficDataFragment a = (AnagraficDataFragment)getFragmentManager().findFragmentById(R.id.pager);
        a.getMBirthDateTextView().setText(DATE_FORMATTER.format(cal.getTime()));
    }

    public void showDatePickerDialog(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DialogFragment newFragment = new DatePickerDialogFragment(StudentProfile.this);
        newFragment.show(ft,"date_picker");
    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
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
    }

}
