package it.polito.mobile.polijobplacement.Search;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import it.polito.mobile.polijobplacement.Data.JobOffers;
import it.polito.mobile.polijobplacement.Data.Jobs_by_Type;
import it.polito.mobile.polijobplacement.R;

public class Search_List_Item extends Fragment {

    private static List<JobOffers> job_by_type;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search__list__item, container, false);
        TextView t = (TextView) v.findViewById(R.id.CategoryName);
        t.setText(job_by_type.get(0).getCategory());
        ListView l = (ListView) v.findViewById(R.id.items);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "Details", Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, Search_List_Item_detail.newInstance(job_by_type.get(position))).commit();

            }
        });
        l.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return job_by_type.size();
            }

            @Override
            public JobOffers getItem(int position) {
                return job_by_type.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item, parent, false);
                TextView t = (TextView) convertView.findViewById(R.id.title_job);
                t.setText(getItem(position).getTitle().toString());
                return convertView;
            }
        });
        return v;
    }



    public  static Search_List_Item newInstance(List<JobOffers> jobs_by_type) {
        job_by_type = jobs_by_type;
        return new Search_List_Item();
    }
}
