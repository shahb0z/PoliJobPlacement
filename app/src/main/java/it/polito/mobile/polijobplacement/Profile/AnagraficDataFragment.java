package it.polito.mobile.polijobplacement.Profile;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import it.polito.mobile.polijobplacement.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnagraficDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnagraficDataFragment#create} factory method to
 * create an instance of this fragment.
 */
public class AnagraficDataFragment extends Fragment {

    private TextView mBirthDateTextView;
    private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-M-yyyy");

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment AnagraficDataFragment.
     */

    public static AnagraficDataFragment create() {
        AnagraficDataFragment fragment = new AnagraficDataFragment();

        return fragment;
    }

    public AnagraficDataFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_anagrafic_data, container, false);
        Spinner spinner = (Spinner)rootView.findViewById(R.id.data_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(new NothingSelectedSpinnerAdapter(adapter,R.layout.contact_spinner_row_nothing_selected,getActivity()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mBirthDateTextView = (TextView)rootView.findViewById(R.id.data_birthdate);
        mBirthDateTextView.setOnClickListener(new View.OnClickListener() {
            // @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int y = c.get(Calendar.YEAR)-4;
                int m = 0;
                int d = 0;

                DatePickerDialog dp = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                c.set(year, monthOfYear, dayOfMonth);
                                mBirthDateTextView.setText(DATE_FORMATTER.format(c.getTime()));

                            }

                        }, y, m, d);
                dp.setTitle("Calender");
                dp.setMessage("Select Your Graduation date Please?");
                dp.getDatePicker().findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
                dp.show();



            }
        });
        return rootView;
    }




    @Override
    public void onDetach() {
        super.onDetach();

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
        public void onFragmentInteraction(Uri uri);
    }

    public TextView getMBirthDateTextView(){
        return mBirthDateTextView;
    }


}
