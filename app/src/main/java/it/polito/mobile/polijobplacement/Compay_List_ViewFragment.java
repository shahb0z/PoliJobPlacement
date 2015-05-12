package it.polito.mobile.polijobplacement;

/**
 * Created by Admin on 5/6/2015.
 */
import java.util.ArrayList;
import java.util.List;
import it.polito.mobile.polijobplacement.Data.Company_2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class Compay_List_ViewFragment extends ListFragment {

    private List<Company_2> mItems;
    private List<Company_2> mItems1;
    // ListView items list
    private String filename = "schedule.json";
    public String compname;
    public String Sector;
    public String Tittle;

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

        JSONParser jParser = new JSONParser();
        JSONObject json;
        json = jParser.loadJSONFromAsset(filename, getActivity()
                .getApplicationContext());
        int datesLength = json.length();
        // cdate= "Monday";
        // String[] dates = new String[datesLength];
        mItems.clear();

        try {

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
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setListAdapter(new Company_ListViewAdapter(getActivity(), mItems));


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
