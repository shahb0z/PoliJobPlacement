package it.polito.mobile.polijobplacement.Search;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import it.polito.mobile.polijobplacement.Data.JobApplication;
import it.polito.mobile.polijobplacement.JobOfferFragment;
import it.polito.mobile.polijobplacement.Profile.NothingSelectedSpinnerAdapter;
import it.polito.mobile.polijobplacement.R;

public class Search_by_filters extends Fragment {
    EditText title;
    EditText location;
    Spinner job_type;
    Button search;
    String type, date;
    EditText company_name;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search_by_filters, container, false);
        title = (EditText) v.findViewById(R.id.title);
        location = (EditText) v.findViewById(R.id.location);
        company_name = (EditText) v.findViewById(R.id.company_name);
        final Spinner spinner = (Spinner)v.findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Spinner sp2 = (Spinner)v.findViewById(R.id.spinner);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                date = sp2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        search = (Button) v.findViewById(R.id.search_job_by_filter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), type + date, Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, Search_Result.newInstance(title.getText().toString(), company_name.getText().toString(),location.getText().toString(), type)).commit();
            }
        });
        return v;
    }

    public static Fragment newInstance() {
        return new Search_by_filters();
    }
}
