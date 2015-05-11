package it.polito.mobile.polijobplacement;

/**
 * Created by Admin on 5/7/2015.
 */
        import java.util.ArrayList;
        import java.util.List;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.os.Bundle;
        import android.support.v4.app.ListFragment;

        import it.polito.mobile.polijobplacement.Data.Inbox;

public class InboxActivity extends ListFragment {
        private List<Inbox> mItems;
        private List<Inbox> mItems1;
        // ListView items list
        private String filename = "schedule.json";
         private static final String TAG_MESSAGES = "Inbox";
        public String From;
        public String subject;
        public String message;
        public String date;

        static public InboxActivity newInstance(String From,String subject, String date, Bundle b) {
                InboxActivity f = new InboxActivity();
                // Supply num input as an argument.
                Bundle args = new Bundle();
                args.putString("From", From);
                args.putString("subject", subject);
                args.putString("date", date);

                if (b != null)
                        args.putAll(b);
                f.setArguments(args);
                return f;
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                Bundle args = getArguments();
                if (args != null) {
                        From =getArguments().getString("From");
                        subject = getArguments().getString("subject");
                        date = getArguments().getString("date");
                }
                //compname = getArguments().getString("compname");

               mItems = new ArrayList<Inbox>();
                mItems1 = new ArrayList<Inbox>();

                JSONParser jParser = new JSONParser();
                JSONObject json;
                json = jParser.loadJSONFromAsset(filename, getActivity()
                        .getApplicationContext());
                int datesLength = json.length();
                // cdate= "Monday";
                // String[] dates = new String[datesLength];
                mItems.clear();

                try {

                        for (int j = 0; j < datesLength; j++) {
                                String dateName = json.names().get(datesLength - (j + 1))
                                        .toString();

                                if (dateName.equals(TAG_MESSAGES)) {

                                        JSONArray schedules = json.getJSONArray(json.names()
                                                .get(datesLength - (j + 1)).toString());
                                        for (int i = 0; i < schedules.length(); i++) {
                                                JSONObject c = schedules.getJSONObject(i);

                                                mItems.add(new Inbox(c.getString("From"), c
                                                        .getString("subject"), c.getString("message"), c
                                                        .getString("date")) );

                                        }

                                } // end of if (dateName.equals(cdate)

                                // /////jj///////
                                // else
                        }
                } catch (JSONException e) {
                        e.printStackTrace();
                }

              setListAdapter(new Inbox_ListViewAdapter(getActivity(), mItems));


        }


}



