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

            Toast.makeText(getActivity().getApplicationContext(), "Not NUll" + l.size(), Toast.LENGTH_LONG).show();
           /* ListView v = (ListView) view.findViewById(R.id.job_list);
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
                public View getView(int position, View convertView, ViewGroup parent) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_search__list__item, parent, false);
                    TextView title =(TextView)convertView.findViewById(R.id.title);
                    TextView company =(TextView) convertView.findViewById(R.id.offeredby);
                    TextView category =(TextView) convertView.findViewById(R.id.category);
                    TextView type =(TextView) convertView.findViewById(R.id.type);
                    TextView salary =(TextView) convertView.findViewById(R.id.salary);
                    TextView post_day =(TextView) convertView.findViewById(R.id.post_day);
                    TextView description =(TextView) convertView.findViewById(R.id.description);
                    TextView deadline =(TextView) convertView.findViewById(R.id.deadline);
                    title.setText(getItem(position).getTitle());
//        company.setText(job.getOfferedBy().getName());
                    category.setText(getItem(position).getCategory());
                    type.setText(getItem(position).getEmploymentType());
                    salary.setText(getItem(position).getSalary());
                    post_day.setText(getItem(position).getPublishDate().toString());
                    description.setText(getItem(position).getDescription());
                    deadline.setText(getItem(position).getDueDate().toString());
                    Button btn = (Button)convertView.findViewById(R.id.apply);
                    final int pos = position;
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity().getApplicationContext(), "Your request is sent", Toast.LENGTH_LONG).show();
                            Messages message = new Messages();
                            message.setFromUser(((JobApplication)getActivity().getApplicationContext()).getUser());
                            message.setToUser(((JobApplication)getActivity().getApplicationContext()).getAppUser(getItem(pos).getOfferedBy().getUserName()));
                            message.setTitle("Request for job");
                            message.saveInBackground();


                        }
                    });
                    return convertView;


                }
            });*/
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
