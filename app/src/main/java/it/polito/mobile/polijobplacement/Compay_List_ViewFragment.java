package it.polito.mobile.polijobplacement;

/**
 * Created by Admin on 5/6/2015.
 */

import java.util.ArrayList;
import java.util.List;
import it.polito.mobile.polijobplacement.Data.Company_2;
import it.polito.mobile.polijobplacement.Data.JobOffers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.ParseException;


public class Compay_List_ViewFragment extends ListFragment {

    private List<Company_2> mItems;
    private List<Company_2> mItems1;
    // ListView items list
    private String filename = "schedule.json";
    public String compname;
    public String Sector;
    public String Tittle;
    public String JobSector;
    public String JobTittle;

    static public Compay_List_ViewFragment newInstance(String cname,
                                                       String sector, String tittle, Bundle b) {
        Compay_List_ViewFragment f = new Compay_List_ViewFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("compname", cname);
        args.putString("Sector", sector);
        args.putString("Tittle", tittle);

        if (b != null)
            args.putAll(b);
        f.setArguments(args);
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            compname =getArguments().getString("compname");
            Sector = getArguments().getString("Sector");
            Tittle = getArguments().getString("Tittle");
        }
        compname = getArguments().getString("compname");

        mItems = new ArrayList<Company_2>();
        mItems1 = new ArrayList<Company_2>();
       JobOffers xx = new JobOffers();
   //TODO  test reading data
       // xx= ReadData();
       //String Cat= xx.getCategory().toString();
        //String Z = xx.getEmploymentType().toString();
        // Instantiate a QueryFactory to define the ParseQuery to be used for fetching items in this
        // Adapter.

                    // Finished Instantiate a QueryFactory to define the ParseQuery to be used for fetching items in this Adapter.


                    JSONParser jParser = new JSONParser();
                    JSONObject json;
                    json=jParser.loadJSONFromAsset(filename,

                    getActivity()

                    .

                    getApplicationContext()

                    );
                    int datesLength = json.length();
                    // cdate= "Monday";
                    // String[] dates = new String[datesLength];
                    mItems.clear();

                    try

                    {

                        for (int j = 0; j < datesLength; j++) {
                            String dateName = json.names().get(datesLength - (j + 1))
                                    .toString();

                            if (dateName.equals(compname)) {

                                JSONArray schedules = json.getJSONArray(json.names()
                                        .get(datesLength - (j + 1)).toString());
                                for (int i = 0; i < schedules.length(); i++) {
                                    JSONObject c = schedules.getJSONObject(i);

                                    mItems.add(new Company_2(c.getString("Name"), c
                                            .getString("Sector"), c.getString("Tittle"), c
                                            .getString("Detail")));

                                }

                            } // end of if (dateName.equals(cdate)

                            // /////jj///////
                            // else
                        }
                    }

                    catch(
                    JSONException e
                    )

                    {
                        e.printStackTrace();
                    }

                    setListAdapter(new Company_ListViewAdapter(getActivity(),mItems

                    ));


                }

                        // Read job offer that much  student skill

    public JobOffers ReadData()

    {  final JobOffers joboffer = new JobOffers();

        ParseQuery<JobOffers> query = ParseQuery.getQuery(JobOffers.class);
   // Define our query conditions
        // Read the user and Company name
       // query.whereEqualTo("zz@gmail.com", ParseUser.getCurrentUser().getEmail());
       // query.whereEqualTo("category", "a");
// ParseQuery<Task> query = ParseQuery.getQuery(Task.class);
        query.findInBackground(new FindCallback<JobOffers>() {
            @Override
            public void done(List<JobOffers> list, ParseException e) {
                if(e==null)
                        {
                            joboffer.setCategory(list.get(0).getCategory());
                            Toast.makeText(getActivity(),joboffer.getCategory(),Toast.LENGTH_SHORT).show();
                        }
                else   {
                    Log.d("item","Error:"+e.getMessage());
                }

            }
        });

        return joboffer;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //updateView(Course,PName);
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        Company_2 item = mItems.get(position);

        mItems1.add(item);

           setListAdapter(new ListViewAdapter_detail(getActivity(), mItems1));

        //Schedule item = mItems.get(position);
        //mItems1.add(item);
        //setListAdapter(new ListViewAdapter_detail(getActivity(), mItems1));
        // do something
        Toast.makeText(getActivity(), item.Tittle, Toast.LENGTH_SHORT)
                .show();
    }

}
