package it.polito.mobile.polijobplacement.Profile;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.polito.mobile.polijobplacement.Data.Degree;
import it.polito.mobile.polijobplacement.Data.Languages;
import it.polito.mobile.polijobplacement.Data.Student;
import it.polito.mobile.polijobplacement.R;


/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {
    public static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-M-yyyy");
    private View header;
    private ArrayList<Item> arrayList = new ArrayList<>();
    private ExpandableListView list;
    private OnFragmentInteractionListener mListener;
    private ExpandableEntryAdapter listAdapter;
    private List<Degree> degreeList;
    private List<Languages> languagesList;
    private List<String> skillList;
    private TextView clicked;


    public static ItemFragment newInstance() {
        ItemFragment fragment = new ItemFragment();

        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item,container,false);
        header = inflater.inflate(R.layout.header,null);
        header.setOnClickListener(null);
        header.setOnLongClickListener(null);
        header.setLongClickable(false);
        list = (ExpandableListView)rootView.findViewById(R.id.degree_list);
        list.addHeaderView(header);
        //section+button
        arrayList.add(new SectionItem("Education"));
        arrayList.add(new ButtonItem("Add a new degree",0));

        arrayList.add(new SectionItem("Skills"));
        arrayList.add(new ButtonItem("Add a new skill",1));

        arrayList.add(new SectionItem("Language Skills"));
        arrayList.add(new ButtonItem("Add a new language skill",2));

        listAdapter = new ExpandableEntryAdapter(getActivity(),arrayList,this,list);
        list.setAdapter(listAdapter);
        list.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void addDegree(String univer, String type, String major,String startDate, String endDate, int pos) {
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if(degreeList == null)
            degreeList = new ArrayList<>();
        if (univer.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_university));
        }
        if (type.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_degree_type));
        }
        if (major.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_degree_major));
        }
        if (startDate == null) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_degree_start_date));
        }
        if (endDate == null) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_degree_end_date));
        }
        validationErrorMessage.append(getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }
        Degree d = new Degree();
        d.setUniversity(univer);
        d.setMajor(major);
        d.setType(type);
        try {
            d.setStartYear(DATE_FORMATTER.parse(startDate));
            d.setEndyear(DATE_FORMATTER.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        degreeList.add(d);
        arrayList.add(pos + 1, new EntryItem(univer, type + ", " + major, 0));
        listAdapter.notifyDataSetChanged();
    }

    public void dateClicked(TextView data_start_year) {
        clicked = data_start_year;
    }

    public void addSkill(String s, int pos) {
        if(skillList == null)
            skillList = new ArrayList<>();
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (s.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_skill));
        }
        validationErrorMessage.append(getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }
        skillList.add(s);
        arrayList.add(pos + 1,new EntryItem(s,"",1));
        listAdapter.notifyDataSetChanged();

    }

    public void addLang(String s, String s1, int pos) {
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if(languagesList == null)
            languagesList = new ArrayList<>();
        if (s.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_language));
        }
        if (s1.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_language_level));
        }
        validationErrorMessage.append(getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }
        Languages l = new Languages();
        l.setName(s);
        l.setLevel(s1);
        languagesList.add(l);
        arrayList.add(pos + 1, new EntryItem(s,s1,2));
        listAdapter.notifyDataSetChanged();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
