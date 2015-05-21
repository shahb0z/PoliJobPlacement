package it.polito.mobile.polijobplacement.Search;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import it.polito.mobile.polijobplacement.Data.JobApplication;
import it.polito.mobile.polijobplacement.Data.JobOffers;
import it.polito.mobile.polijobplacement.Data.Messages;
import it.polito.mobile.polijobplacement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_List_Item_detail extends android.support.v4.app.Fragment {

    private static JobOffers job;
    public Search_List_Item_detail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search__list__item_detail, container, false);
        TextView title =(TextView) v.findViewById(R.id.title);
        TextView company =(TextView) v.findViewById(R.id.offeredby);
        TextView category =(TextView) v.findViewById(R.id.category);
        TextView type =(TextView) v.findViewById(R.id.type);
        TextView salary =(TextView) v.findViewById(R.id.salary);
        TextView post_day =(TextView) v.findViewById(R.id.post_day);
        TextView description =(TextView) v.findViewById(R.id.description);
        TextView deadline =(TextView) v.findViewById(R.id.deadline);
        title.setText(job.getTitle());
//        company.setText(job.getOfferedBy().getName());
        category.setText(job.getCategory());
        type.setText(job.getEmploymentType());
        salary.setText(job.getSalary());
        post_day.setText(job.getPublishDate().toString());
        description.setText(job.getDescription());
        deadline.setText(job.getDueDate().toString());
        Button btn = (Button)v.findViewById(R.id.apply);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Messages message = new Messages();
                message.setFromUser(((JobApplication)getActivity().getApplicationContext()).getUser());
                message.setToCompany(job.getOfferedBy());
                message.setTitle("Request for job");
                message.saveInBackground();
                Toast.makeText(getActivity().getApplicationContext(), "Your request is sent", Toast.LENGTH_LONG).show();


            }
        });
        return v;
    }


    public static Search_List_Item_detail newInstance(JobOffers jobOffers) {
        job = jobOffers;
        return new Search_List_Item_detail();
    }
}
