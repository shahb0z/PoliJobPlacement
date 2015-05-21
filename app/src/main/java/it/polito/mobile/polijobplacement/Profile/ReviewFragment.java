package it.polito.mobile.polijobplacement.Profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import it.polito.mobile.polijobplacement.Data.Degree;
import it.polito.mobile.polijobplacement.Data.Languages;
import it.polito.mobile.polijobplacement.Data.Student;
import it.polito.mobile.polijobplacement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {


    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);
        PageList list = ((StudentProfile)getActivity()).getmFragmentList();
        TextView name = (TextView)rootView.findViewById(R.id.review_name);
        TextView surname = (TextView)rootView.findViewById(R.id.review_surname);
        TextView email = (TextView)rootView.findViewById(R.id.review_email);
        TextView gender = (TextView)rootView.findViewById(R.id.review_gender);
        TextView birthdate = (TextView)rootView.findViewById(R.id.review_birthdate);
        TextView country = (TextView)rootView.findViewById(R.id.review_country);
        TextView region = (TextView)rootView.findViewById(R.id.review_region);
        TextView nation = (TextView)rootView.findViewById(R.id.review_nationality);
        TextView degrees = (TextView)rootView.findViewById(R.id.review_degree);
        TextView languages = (TextView)rootView.findViewById(R.id.review_language);
        TextView skills = (TextView)rootView.findViewById(R.id.review_skill);
        name.setText(((AnagraficDataFragment)list.getList().get(0)).getName().getText().toString());
        surname.setText(((AnagraficDataFragment)list.getList().get(0)).getSurname().getText().toString());
        email.setText(((AnagraficDataFragment)list.getList().get(0)).getEmail().getText().toString());
        gender.setText(((AnagraficDataFragment)list.getList().get(0)).getGender().getSelectedItem().toString());
        birthdate.setText(((AnagraficDataFragment)list.getList().get(0)).getmBirthDateTextView().getText().toString());
        country.setText(((AddressDataFragment)list.getList().get(1)).getCountry().getText().toString());
        region.setText(((AddressDataFragment) list.getList().get(1)).getRegion().getText().toString());
        nation.setText(((AddressDataFragment)list.getList().get(1)).getNation().getText().toString());
        String s = "";
        List<Degree> degreeList = ((ItemFragment)list.getList().get(2)).getDegreeList();
        for(Degree d : degreeList){
            s += d.getUniversity() + "\n" + d.getType() + ", " + d.getMajor() + "\n";
        }
        degrees.setText(s);
        s = "";
        List<String> skillList = ((ItemFragment)list.getList().get(2)).getSkillList();
        for(String sk : skillList){
            s += sk + ", ";
        }
        skills.setText(s);
        s = "";
        List<Languages> languagesList = ((ItemFragment)list.getList().get(2)).getLanguagesList();
        for(Languages d : languagesList){
            s += d.getName() + ", " + d.getLevel() + "\n";
        }
        languages.setText(s);
        return rootView;
    }


    public static Fragment newInstance() {
        ReviewFragment r = new ReviewFragment();
        return r;
    }
}
