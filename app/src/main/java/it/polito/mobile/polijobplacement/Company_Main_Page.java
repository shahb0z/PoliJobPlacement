package it.polito.mobile.polijobplacement;

import android.os.Bundle;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import it.polito.mobile.polijobplacement.Search.Company_Home;
import it.polito.mobile.polijobplacement.Search.Search_Specific;
import it.polito.mobile.polijobplacement.Search.Search_student;

public class Company_Main_Page extends ActionBarActivity {



    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mDrawerItmes;
    private ActionBar actionBar;
     private int icons[] = {R.drawable.ic_home,
                            R.drawable.ic_action_edit,
                            R.drawable.ic_search,
                            R.drawable.ic_action_new,
                            R.drawable.ic_action_forward
                            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__main__page);
        //FragmentPageAdapter f = new FragmentPageAdapter();
       // getSupportFragmentManager().beginTransaction().add(R.id.content_frame, Company_Home.newInstance()).commit();

        mTitle = mDrawerTitle = getTitle();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerItmes = getResources().getStringArray(R.array.drawer_title_company);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer
        // opens
        mDrawerLayout.setDrawerShadow(R.drawable.plus, GravityCompat.START);

        // Add items to the ListView
        mDrawerList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mDrawerItmes.length;
            }

            @Override
            public String getItem(int position) {
                return mDrawerItmes[position];
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.company_drawer_list_item, parent, false);
                ImageView i = (ImageView)convertView.findViewById(R.id.image);
                TextView tv = (TextView) convertView.findViewById(R.id.titles);
                tv.setText(getItem(position).toString());
                i.setImageResource(icons[position]);
                return convertView;
            }
        });
        //Adapter <String>(this,
        //R.layout.drawer_list_item, mDrawerItmes));
        // Set the OnItemClickListener so something happens when a
        // user clicks on an item.
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_ab_drawer, R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {

                getSupportActionBar().setTitle(mTitle);
                // actionBar.setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Set the default content area to item 0
        // when the app opens for the first time
        if (savedInstanceState == null) {
            navigateTo(0);
        }

    }

    /*
     * If you do not have any menus, you still need this function in order to
     * open or close the NavigationDrawer when the user clicking the ActionBar
     * app icon.
     */

	/*
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()
	 */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            navigateTo(position);

        }
    }

    private void navigateTo(int position) {

        switch (position) {
            case 0:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, Company_Home.newInstance()).commit();
                break;
          /* case 1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, ProfileManagement.newInstance(),
                               ProfileManagement.TAG).commit();
                break;*/
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, Search_student.newInstance()).commit();
              break;
            //case 3:

            case 4:
                startActivity(new Intent(getApplicationContext(), MainActivity1.class));
                break;
        }
        //mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_home, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_search) {
            startActivity(new Intent(getApplicationContext(), Search_Specific.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
