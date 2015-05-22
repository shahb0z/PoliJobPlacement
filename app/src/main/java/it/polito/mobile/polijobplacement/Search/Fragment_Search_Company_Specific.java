package it.polito.mobile.polijobplacement.Search;


import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import it.polito.mobile.polijobplacement.R;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class Fragment_Search_Company_Specific extends android.support.v4.app.Fragment {
    Spinner sp1;
    Spinner sp2;
    EditText location;
    EditText skills;
    EditText language;
    Spinner sp3;
    String field, degree, level;
    Button btn;

    public Fragment_Search_Company_Specific() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment_search, container, false);
        sp1 = (Spinner)v.findViewById(R.id.field);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                field = sp1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp2 = (Spinner)v.findViewById(R.id.degree);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                degree = sp2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp3 = (Spinner)v.findViewById(R.id.languge_level);
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                level = sp3.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        location = (EditText)v.findViewById(R.id.location);
        language = (EditText)v.findViewById(R.id.language);
        skills = (EditText)v.findViewById(R.id.skills);
        btn = (Button)v.findViewById(R.id.search_btn);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, Search_Student_Result.newInstance(field,degree, level,location.getText().toString(), skills.getText().toString(), language.getText().toString())).commit();
            }
        });
        return v;
    }


}
