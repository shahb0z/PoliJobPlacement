package it.polito.mobile.polijobplacement.Profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import it.polito.mobile.polijobplacement.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressDataFragment extends Fragment {


    public EditText getCountry() {
        return country;
    }

    public EditText getRegion() {
        return region;
    }

    public EditText getNation() {
        return nation;
    }

    private EditText country;
    private EditText region;
    private EditText nation;




    public static AddressDataFragment newInstance() {
        AddressDataFragment fragment = new AddressDataFragment();
        return fragment;
    }

    public AddressDataFragment() {
        // Required empty public constructor
    }

    public boolean isCompleted() {
        if(country.getText().toString() == null||
                region.getText().toString() == null
                )
            return false;
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_address_data, container, false);
        country = (EditText)rootView.findViewById(R.id.country_edit_text);
        region = (EditText)rootView.findViewById(R.id.region_edit_text);
        nation = (EditText)rootView.findViewById(R.id.nationality_edit_text);
        return rootView;
    }


}
