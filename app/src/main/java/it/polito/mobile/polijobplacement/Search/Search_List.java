package it.polito.mobile.polijobplacement.Search;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.polito.mobile.polijobplacement.Data.JobApplication;
import it.polito.mobile.polijobplacement.Data.JobOffers;
import it.polito.mobile.polijobplacement.Data.Jobs_by_Type;
import it.polito.mobile.polijobplacement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_List extends android.support.v4.app.Fragment {

    private static List<Jobs_by_Type> jobs;
    private static HashMap<String, List<JobOffers>> jbt;

    public  static Search_List newInstance() {
        // Required empty public constructor
        jbt = new HashMap<String, List<JobOffers>>();
        return new Search_List();
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search__list, container, false);

        ListView l = (ListView)v.findViewById(R.id.list);
        JobApplication data = (JobApplication) getActivity().getApplicationContext();
       /* try {
            create_jobs_by_list();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        //jobs = data.getJobsbyType();
        create_jobs_by_list();
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<JobOffers> joboffers = null;
                int i =0;
                for( String s: jbt.keySet()){

                    if(i==position){
                        joboffers = jbt.get(s);
                    }
                    i++;


                }
                Toast.makeText(getActivity().getApplicationContext(),"myPos " + position, Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, Search_List_Item.newInstance(joboffers)).commit();

            }
        });

        if( !jbt.isEmpty()) {
            l.setAdapter(new BaseAdapter() {

                @Override
                public int getCount() {
                    return jbt.size();
                }

                @Override
                public List<JobOffers> getItem(int position) {
                    int i=0;
                    for( String s: jbt.keySet()){
                        if(i==position){
                            return jbt.get(s);
                        }
                        i++;


                    }
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    convertView = inflater.inflate(R.layout.search_item_list, parent, false);
                    TextView categoryName = (TextView) convertView.findViewById(R.id.category_name);
                    TextView amount = (TextView) convertView.findViewById(R.id.amount);
                    categoryName.setText(getItem(position).get(0).getCategory());
                    amount.setText("(" + getItem(position).size() + ")");
                    return convertView;

                }
            });
        }

        return v;
    }


    public void create_jobs_by_list()  {

        ParseQuery<JobOffers> query = ParseQuery.getQuery(JobOffers.class);

        List<JobOffers> ljob = null;
        try {
            ljob = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < ljob.size(); i++) {
            String type = ljob.get(i).getCategory();
            if (jbt.containsKey(type) )
            {
                boolean flag =true;
                for( JobOffers j: jbt.get(type)){
                    if(j.getObjectId().equals(ljob.get(i).getObjectId())){
                        flag =false;
                    }

                }
                if( flag){
                    jbt.get(type).add(ljob.get(i));
                }

             //  ParseQuery<Jobs_by_Type> query2 = ParseQuery.getQuery(Jobs_by_Type.class);

              //  query2.whereEqualTo("type", type);
                //if( query2.find().size() != 0) {
                  //  Jobs_by_Type old = query2.find().get(0);
                    //old.getList_jobs().add(ljob.get(i));
                    //old.saveEventually();

                //}
            }
            if( !jbt.containsKey(type)) {
                List<JobOffers> l = new ArrayList<JobOffers>();
                //Jobs_by_Type jbtnew = new Jobs_by_Type();
                //jbtnew.setTYPE(type);
                //jbtnew.add(ljob.get(i));
                //jbtnew.saveInBackground();
                l.add(ljob.get(i));
                jbt.put(type, l);

            }
        }

    }

}
