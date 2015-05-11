package it.polito.mobile.polijobplacement;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import it.polito.mobile.polijobplacement.Data.JobApplication;
import it.polito.mobile.polijobplacement.Search.Search;

public class StudentMainPageActivity extends ActionBarActivity {



    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mDrawerItmes;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FragmentPageAdapter f = new FragmentPageAdapter();
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, TabbedFragment.newInstance()).commit();

        mTitle = mDrawerTitle = getTitle();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerItmes = getResources().getStringArray(R.array.drawer_titles);
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
                                   public Object getItem(int position) {
                                       return mDrawerItmes[position];
                                   }

                                   @Override
                                   public long getItemId(int position) {
                                       return 0;
                                   }

                                   @Override
                                   public View getView(int position, View convertView, ViewGroup parent) {
                                      convertView = getLayoutInflater().inflate(R.layout.drawer_listitem, parent, false);
                                      // ImageView i = (ImageView)convertView.findViewById(R.id.image);
                                       TextView tv = (TextView) convertView.findViewById(R.id.text1);
                                       tv.setText(getItem(position).toString());

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
                        .replace(R.id.content_frame, TabbedFragment.newInstance(),
                                TabbedFragment.TAG).commit();
                break;
          /* case 1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, ProfileManagement.newInstance(),
                               ProfileManagement.TAG).commit();
                break;*/
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, Search.newInstance()).commit();
                break;
            case 5:
                JobApplication db = (JobApplication)getApplication().getApplicationContext();

                startActivity(new Intent(getApplicationContext(), MainActivity1.class));

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
    public boolean onPrepareOptionsMenu(Menu menu) {
		/*
		 * MenuItem logToggle = menu.findItem(R.id.menu_toggle_log);
		 * logToggle.setVisible(findViewById(R.id.sample_output) instanceof
		 * ViewAnimator); logToggle.setTitle(mLogShown ?
		 * R.string.sample_hide_log : R.string.sample_show_log);
		 */
        // MenuItem searchItem = menu.findItem(R.id.menu_search);
        // mSearchView = (SearchView) searchItem.getActionView();
        // //mSearchView.setEnabled(true);

        return super.onPrepareOptionsMenu(menu);
    }

	/*
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { boolean
	 * mSearchOpened = true; switch (item.getItemId()) {
	 *
	 * case R.id.search: { Toast.makeText(this, "Tapped search",
	 * Toast.LENGTH_SHORT).show(); { final Intent intent = new
	 * Intent(Intent.ACTION_SEARCH );
	 *
	 * if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
	 *
	 * String query = intent.getStringExtra(SearchManager.QUERY);
	 * Toast.makeText(getApplicationContext(), "Searching",
	 * Toast.LENGTH_SHORT).show();} // startActivity(intent);
	 * setupSearchView(item); } }
	 *
	 * break;
	 *
	 * case R.id.menu_search: { Toast.makeText(this, "Tapped share",
	 * Toast.LENGTH_SHORT).show(); setupSearchView(item); } break; } return
	 * super.onOptionsItemSelected(item); //
	 * //////////////////ante//////////////////// // return MenuChoice(item); }
	 */

    private void CreateMenu(Menu menu) {
        MenuItem mnu1 = menu.add(0, 0, 0, "Setting");
        {

            mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        }
        MenuItem mnu2 = menu.add(0, 1, 0, "Setting1");
        {

            mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        }
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search)

                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

    }

    // TODO//////////////////////////////////////////////
    @Override
    public boolean onSearchRequested() {

        // Toast.makeText(getActivity(), item.professorName, Toast.LENGTH_SHORT)
        // .show();
        // Toast.makeText(getApplication(), 555, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "YES", Toast.LENGTH_SHORT)
                .show();
        return super.onSearchRequested();
    }


    // /////////////////////////////////////////////search////////////////
    // test//////////////////
    private void setupSearchView(MenuItem searchItem) {

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(getApplicationContext(), "Searching",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
