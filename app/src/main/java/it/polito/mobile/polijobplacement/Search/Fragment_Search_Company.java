package it.polito.mobile.polijobplacement.Search;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import it.polito.mobile.polijobplacement.Data.App_User;
import it.polito.mobile.polijobplacement.Data.JobApplication;
import it.polito.mobile.polijobplacement.Data.JobOffers;
import it.polito.mobile.polijobplacement.Data.Student;
import it.polito.mobile.polijobplacement.R;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class Fragment_Search_Company extends android.support.v4.app.Fragment {
    public static final  String STUDENT = "student";

    public Fragment_Search_Company() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment__home, container, false);
        JobApplication db = (JobApplication) getActivity().getApplicationContext();
        final List<Student> list_students = db.Student_List();
        if(list_students != null) {
            ListView lt = (ListView) v.findViewById(R.id.list_company);

            lt.setAdapter(new BaseAdapter() {

                @Override
                public int getCount() {
                    return list_students.size();
                }

                @Override
                public Object getItem(int position) {
                    return list_students.get(position).getUserName();
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = getActivity().getLayoutInflater().inflate(R.layout.list_company, parent, false);
                        TextView tv = (TextView) convertView.findViewById(R.id.company_name);
                        tv.setText(getItem(position).toString());
                        TextView detail = (TextView) convertView.findViewById(R.id.company_detail);
                        detail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getActivity().getApplicationContext(), Detail_Student.class);
                                i.putExtra(STUDENT, getItem(position).toString());
                                startActivity(i);
                            }
                        });
                    }
                    return convertView;
                }
            });
        }
        return v;
    }


}
