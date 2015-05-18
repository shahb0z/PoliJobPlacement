package it.polito.mobile.polijobplacement;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import it.polito.mobile.polijobplacement.Data.*;
import it.polito.mobile.polijobplacement.Profile.CompanyProfile;

import java.util.Iterator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JobOfferFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JobOfferFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobOfferFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "";
    // TODO: Rename and change types of parameters
    List<String> mItems;
    List<String[]> mItems1;
    private String filename ="schedule.json";
    private String mParam1;
    private String mParam2;
    /////////////////////////////////////////////////////////////////
    public static final String EMPLOYMENT_FULL_TIME = "Full Time";
    public static final String EMPLOYMENT_PART_TIME = "Part Time";
    public static final String EMPLOYMENT_CONTRACTOR = "Contractor";
    public static final String EMPLOYMENT_INTERN = "Intern";
    public static final String EMPLOYMENT_SEASONAL = "Seasonal";

    //////////////////////////////////////////
    private String EmpType[] = {EMPLOYMENT_CONTRACTOR, EMPLOYMENT_FULL_TIME, EMPLOYMENT_PART_TIME, EMPLOYMENT_INTERN, EMPLOYMENT_SEASONAL};
   private String TermContract[]={"up to 1yr","1-2yr","2-4yr","above 4 yr"};
    private String SALARY_RANGE[] = {"up to 1000$", "1000- 2000$", "2000-4000&", "4000-6000$", "6000-8000$", "8000-10,000$", "above 10,000$"};
    String CurrentUser;
    // String  offeredBy;
    DatePicker expdatePicker;
    View root;
    protected  JobOffers  newJobOffers;
    protected JobOffers offer;
    protected App_User userData;
    protected  Company comp;
    protected  Company comp1;
    protected  JobApplication GolabalData;
    protected String title,description,salary,publishdate,category,employmenttype,applicantList,offeredBy,dueDate;
  protected  Spinner SpinnerJob_Sector;
    protected  Spinner SpinnerSkill;
   /* protected String publishdate;
    protected String category;
    protected String employmenttype;
     protected String applicantList;
    protected String offeredBy;
  //  protected  String salary;
    protected String dueDate;*/

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JobOfferFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JobOfferFragment newInstance(String param1, String param2) {


        JobOfferFragment fragment = new JobOfferFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public JobOfferFragment() {
        super();
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
/*
            offer= new JobOffers();
            offer.setEmploymentType(JobApplication.EMPLOYMENT_CONTRACTOR);*/
            GolabalData = new JobApplication();
            userData = new App_User();
            offer = new JobOffers();
            comp  = new Company();
           //userData = GolabalData.getUser();
           // TestData();

            if (userData == null) {
                //getFragmentManager().popBackStackImmediate();
            }
                  /*Currently no date*/
            // offeredBy=userData.getEmail(); // company name to check delete later
            // company.setName(userData.getName());

            // company.fetchIfNeededInBackground();

            // retrieveJobOfferInfo();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.job_newoffer, container, false);
        Spinner typeContractList = (Spinner) root.findViewById(R.id.typeContract);
        Spinner SalaryList = (Spinner) root.findViewById(R.id.spinnerSalary);
        Spinner spinnerDuration =(Spinner)root.findViewById(R.id.spinnerDuration );
        //  to read from xml list for spinner
        SpinnerJob_Sector = (Spinner)root.findViewById(R.id.Job_Sector);
        SpinnerSkill = (Spinner)root.findViewById(R.id.Skill);
        getResources().getTextArray(R.array.new_offer_fragment_termContracts);



        SpinnerSkill.setAdapter(new StringAdapter(readSkils()));
        SpinnerJob_Sector.setAdapter(new StringAdapter(readCatagory()));
        spinnerDuration.setAdapter(new StringAdapter(TermContract));
        typeContractList.setAdapter(new StringAdapter(EmpType));
        SalaryList.setAdapter(new StringAdapter(SALARY_RANGE));

        DatePicker expdatePicker = (DatePicker) root.findViewById(R.id.Expied_datePicker);
        Date ExpDate = null;
        Calendar c = GregorianCalendar.getInstance();
        c.set(expdatePicker.getYear(), expdatePicker.getMonth(), expdatePicker.getDayOfMonth());
        ExpDate = c.getTime();
        final Button btnPostjob= (Button)root.findViewById(R.id.publishOfferbutton);


        btnPostjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           if(!comp.equals(null))
                      {

                         //final App_User temp = new App_User();

                        //comp = AddJobToCompany();
                            offer  = retrieveJobOfferInfo();
                           //offer.saveInBackground();
                          offer.saveInBackground(new SaveCallback() {
                               @Override
                               public void done(ParseException e) {

                                   if (e == null) {
                                       // Saved successfully.
                                       btnPostjob.setBackgroundColor(Color.RED);
                                       Toast.makeText(getActivity(), "Data Saved Suceesfully", Toast.LENGTH_LONG).show();

                                       Log.d(TAG, "JobOffer update saved!");



                                       Log.d(TAG, "The object id (from User) is: " );
                                   } else {
                                       // The save failed.
                                       Log.d(TAG, "User update error: " + e);
                                   }

                               }
                           });
                      }
           else {
               Toast.makeText(getActivity(), "Object is Null", Toast.LENGTH_LONG).show();

           }

            }

           });


          return root;


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public  void TestData()
    {  App_User user = new App_User();
        user=(App_User)ParseUser.getCurrentUser();
        user.getObjectId();
        String usertype = user.getType();

        String Username =  user.getUsername();
        Company testComp = new Company();
       // testComp.setUser_test(user);
        testComp.setName(user.getUsername());
        testComp.setDetail("Detail");
        testComp.setCity("ROMA");
        testComp.setField("Computer");
        testComp.setCountry("Italy ");
        if(!testComp.equals(null))
        {
             testComp.saveInBackground(new SaveCallback() {
                 @Override
                 public void done(ParseException e) {

                     if (e == null) {
                         // Saved successfully.
                         Log.d(TAG, "Company   saved!");


                         Log.d(TAG, "The object id (from User) is: " );
                     }
                     else
                     {
                         // The save failed.
                         Log.d(TAG, "Company  update error: " + e);
                     }
                 }
             });


        }


    }
 // read CatagorylistNames from local
 public String[] readSkils() {

     String[] stringArray = new String[50];
     JSONParser jParser = new JSONParser();
     JSONObject json;
     json = jParser.loadJSONFromAsset(filename, getActivity()
             .getApplicationContext());
     int datesLength = json.length();

     try {

         for (int j = 0; j < datesLength; j++) {
             String dateName = json.names().get(datesLength - (j + 1))
                     .toString();
             if (dateName.equals("Job_Catagory")) {
                 List<String> list = new ArrayList<String>();
                 JSONArray schedules = json.getJSONArray(json.names()
                         .get(datesLength - (j + 1)).toString());
                 for (int i = 0; i < schedules.length(); i++) {
                     JSONObject c = schedules.getJSONObject(i);
                     String msg = c.getString("Sector");
                     list.add(msg);
                 }
                 stringArray = list.toArray(new String[list.size()]);

             }


         }
     } catch (JSONException e) {
         e.printStackTrace();
     }
     return  stringArray;
 }
    public String[] readCatagory() {

        String[] stringArray = new String[50];
        JSONParser jParser = new JSONParser();
        JSONObject json;
        json = jParser.loadJSONFromAsset(filename, getActivity()
                .getApplicationContext());
        int datesLength = json.length();

        try {

            for (int j = 0; j < datesLength; j++) {
                String dateName = json.names().get(datesLength - (j + 1))
                        .toString();
                if (dateName.equals("Job_Catagory")) {
                    List<String> list = new ArrayList<String>();
                    JSONArray schedules = json.getJSONArray(json.names()
                            .get(datesLength - (j + 1)).toString());
                    for (int i = 0; i < schedules.length(); i++) {
                        JSONObject c = schedules.getJSONObject(i);
                           String msg = c.getString("Sector");
                          list.add(msg);
                    }
                   stringArray = list.toArray(new String[list.size()]);

                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  stringArray;
    }

    public class StringAdapter extends BaseAdapter {

        public String[] stringArray;

        public StringAdapter(String[] stringArray){
            super();
            this.stringArray = stringArray;
        }

        @Override
        public int getCount() {
            return stringArray.length;
        }

        @Override
        public String getItem(int position) {
            return stringArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null)
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_text_element,parent,false);

            TextView text = (TextView)convertView.findViewById(R.id.text_view);
            text.setText(stringArray[position]);
            return convertView;
        }
    }
    public Company AddJobToCompany(){

        comp1  = new Company();
        comp1.setDetail("detail");
         comp1 .setField("Filed");;


       // comp1.addItemToList_offers(retrieveJobOfferInfo());
        return   comp1;

    }
    //TODO
    public JobOffers retrieveJobOfferInfo() {
      List<String> skilllist = null;

       newJobOffers = new JobOffers();
        comp = new Company();
        App_User user = new App_User();
        user=(App_User)ParseUser.getCurrentUser();


        //EditText offerObject =(EditText)root.findViewById(R.id.offerObject);
        EditText Job_Tittle =(EditText)root.findViewById(R.id.Job_Tittle);
        EditText Available_space =(EditText)root.findViewById(R.id.Available_space);
        EditText Description =(EditText)root.findViewById(R.id.DescriptionText);
        //EditText JobCatagory =(EditText)root.findViewById(R.id.Job_Sector);




        Date ExpDate = null;
        Calendar c = GregorianCalendar.getInstance();
        DatePicker expdatePicker = (DatePicker) root.findViewById(R.id.Expied_datePicker);
        c.set(expdatePicker.getYear(), expdatePicker.getMonth(), expdatePicker.getDayOfMonth());
        ExpDate = c.getTime();



        //Spinner jobCatagoryList = (Spinner)root.findViewById(R.id.Catagory);
       // String jobCatagory = (String)jobCatagoryList.getSelectedItem();
        String JobCatagory  = (String)SpinnerJob_Sector.getSelectedItem();
        Spinner typeContractList = (Spinner) root.findViewById(R.id.typeContract);
        Spinner SalaryList = (Spinner)root.findViewById(R.id.spinnerSalary);
        Spinner spinnerDuration =(Spinner)root.findViewById(R.id.spinnerDuration);

        String  ContractType  = (String)typeContractList.getSelectedItem();
        String Skil   = (String) SpinnerSkill.getSelectedItem();
        skilllist.add(Skil);
        String ContratYr =   (String)spinnerDuration.getSelectedItem();
        // One of the Two value of salary
       salary = (String)SalaryList.getSelectedItem();
        EditText Salaryoffer=(EditText)root.findViewById(R.id.offerSalary);
        int val = Integer.parseInt(Salaryoffer.getText().toString());
        /// ToDo ??? Validation


           
          newJobOffers.setSalary(val);
         newJobOffers.setfor_test(user);
        // TODO Update later??????????????  Type of emp
         newJobOffers.setEmploymentType(EMPLOYMENT_FULL_TIME);
         newJobOffers.setCategory(JobCatagory);
        // TODO  one or more skill list
       // newJobOffers.setSkillsList(skilllist);
      //  newJobOffers.setOfferedBy(comp);
        //  TODO  change to System.Today Date
        newJobOffers.setPublishDate(ExpDate);
        newJobOffers.setDueDate(ExpDate);
        newJobOffers.setDescription(Description.getText().toString());
        newJobOffers.setTitle(Job_Tittle.getText().toString());

        //TODO
        //newJobOffers.setOfferedBy();
       // newJobOffers.setLanguage();
       // newJobOffers.setSkillsList();



        return newJobOffers;
    }

    }


