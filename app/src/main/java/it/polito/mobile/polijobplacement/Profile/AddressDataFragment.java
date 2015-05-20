package it.polito.mobile.polijobplacement.Profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.polito.mobile.polijobplacement.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressDataFragment extends Fragment {







    public static AddressDataFragment newInstance() {
        AddressDataFragment fragment = new AddressDataFragment();
        return fragment;
    }

    public AddressDataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address_data, container, false);
    }


}
