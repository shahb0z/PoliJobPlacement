package it.polito.mobile.polijobplacement.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import it.polito.mobile.polijobplacement.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentProfileFragment extends Fragment {

    public static final String TAG = StudentProfileFragment.class.getSimpleName();

    public static StudentProfileFragment newInstance() {
        StudentProfileFragment fragment = new StudentProfileFragment();
        return fragment;
    }

    public StudentProfileFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_student_profile, container, false);
        Button edit = (Button)rootView.findViewById(R.id.edit_profile);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),StudentProfile.class));
            }
        });
        return rootView;
    }


}
