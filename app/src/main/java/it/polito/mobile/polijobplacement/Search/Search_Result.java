package it.polito.mobile.polijobplacement.Search;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;

import java.util.List;

import it.polito.mobile.polijobplacement.Data.JobApplication;
import it.polito.mobile.polijobplacement.Data.JobOffers;
import it.polito.mobile.polijobplacement.Data.Messages;
import it.polito.mobile.polijobplacement.R;

public class Search_Result extends Fragment {

    private static String title;
    private static String location;
    private static  String type;
    private static String company;
    List<JobOffers> l = null;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search__result, container, false);


        TextView t = (TextView)view.findViewById(R.id.resultTitle);
        t.setText(title);

        JobApplication data = (JobApplication) getActivity().getApplicationContext();

        try {
            l = data.getJobList(title, company, location, type);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if( l == null){
            Toast.makeText(getActivity().getApplicationContext(), "NUll", Toast.LENGTH_LONG).show();
        }
        if( l != null) {


            String title = l.get(0).getTitle();
            String job_type = l.get(0).getEmploymentType();
            String name = l.get(0).getCategory();
            Toast.makeText(getActivity().getApplicationContext(), "Not NUll" + l.size() + title + job_type + name, Toast.LENGTH_LONG).show();
            ListView v = (ListView) view.findViewById(R.id.job_list);
            v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity().getApplicationContext(), "Details", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, Search_Result_detail.newInstance(l.get(position))).commit();

                }
            });
            v.setAdapter(new BaseAdapter(){
                @Override
                public int getCount() {
                    return l.size();
                }

                @Override
                public JobOffers getItem(int position) {
                    return l.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.item, parent, false);
                    TextView t = (TextView) convertView.findViewById(R.id.title_job);
                    t.setText(getItem(position).getTitle().toString());
                    return convertView;
                }
            });
        }
        return view;
    }




    public static Fragment newInstance(String s, String text, String text1, String t) {
        title = s;
        location = text1;
        type = t;
        company = text;
        return new Search_Result();
    }
}
