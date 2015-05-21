package it.polito.mobile.polijobplacement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.polito.mobile.polijobplacement.Data.App_User;
import it.polito.mobile.polijobplacement.Data.Messages;


public class inboxFragment extends Fragment {

    ParseQuery<Messages> pq;
    ArrayList<Messages> messages=new ArrayList<Messages>();
    ListView list;
    Button btn;
    Messages current;
    EditText feedback;
    public static inboxFragment newInstance(String From,String subject, String date, Bundle b) {
        return new inboxFragment();
    }

    public inboxFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.inbox_list, container, false);
        list = (ListView) root.findViewById(R.id.list);

        messages.clear();
        App_User app_user= App_User.getCurrentUser();
        pq=  ParseQuery.getQuery(Messages.class);
        pq.whereEqualTo("to",app_user);
        pq.findInBackground(new FindCallback<Messages>() {
            @Override
            public void done(List<Messages> messageses, ParseException e) {
                if (e==null) {
                    messages.addAll(messageses);
                    list.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                            return messages.size();
                        }

                        @Override
                        public Object getItem(int position) {
                            return messages.get(position);
                        }

                        @Override
                        public long getItemId(int position) {
                            return 0;
                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            ViewHolder viewHolder;

                            if (convertView == null) {
                                // inflate the GridView item layout
                                LayoutInflater inflater = LayoutInflater.from(getActivity());
                                convertView =inflater.inflate(R.layout.inbox_list_item, parent, false);
                                //convertView = inflater.inflate(R.layout.job_detail, parent, false);

                                // initialize the view holder
                                viewHolder = new ViewHolder();
                                viewHolder.From = (TextView) convertView
                                        .findViewById(R.id.from);
                                viewHolder.subject = (TextView) convertView
                                        .findViewById(R.id.subject);
                                viewHolder.date = (TextView) convertView.findViewById(R.id.date);
                                convertView.setTag(viewHolder);
                            } else {
                                // recycle the already inflated view
                                viewHolder = (ViewHolder) convertView.getTag();
                            }

                            // update the item view
                            ParseQuery<App_User> user=  ParseQuery.getQuery(App_User.class);
                            try {
                                App_User temp= user.get( messages.get(position).getFromUser().getObjectId());
                                viewHolder.From.setText( temp.getEmail());
                                viewHolder.subject.setText(messages.get(position).getTitle());

                                Date date =messages.get(position).getUpdatedAt();
                                viewHolder.date.setText(date.toGMTString());
                                return convertView;

                            }
                            catch (Exception ex)
                            {
                                return null;
                            }





                        }
                    });
                }
            }
        });


        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return messages.size();
            }

            @Override
            public Object getItem(int position) {
                return messages.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder;

                if (convertView == null) {
                    // inflate the GridView item layout
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    convertView =inflater.inflate(R.layout.inbox_list_item, parent, false);
                    //convertView = inflater.inflate(R.layout.job_detail, parent, false);

                    // initialize the view holder
                    viewHolder = new ViewHolder();
                    viewHolder.From = (TextView) convertView
                            .findViewById(R.id.from);
                    viewHolder.subject = (TextView) convertView
                            .findViewById(R.id.subject);
                    viewHolder.date = (TextView) convertView.findViewById(R.id.date);
                    convertView.setTag(viewHolder);
                } else {
                    // recycle the already inflated view
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                // update the item view

                ParseQuery<App_User> user=  ParseQuery.getQuery(App_User.class);
                try {
                    App_User temp= user.get( messages.get(position).getFromUser().getObjectId());
                    viewHolder.From.setText( temp.getEmail());
                    viewHolder.subject.setText(messages.get(position).getTitle());
                    Date date =messages.get(position).getUpdatedAt();
                    viewHolder.date.setText(date.toGMTString());

                    return convertView;

                }
                catch (Exception ex)
                {
                    return null;
                }

            }
        });




        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
        fab.attachToListView(list, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
                Log.d("ListViewFragment", "onScrollDown()");
            }

            @Override
            public void onScrollUp() {
                Log.d("ListViewFragment", "onScrollUp()");
            }
        }, new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("ListViewFragment", "onScrollStateChanged()");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("ListViewFragment", "onScroll()");
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),sendNewMessage.class));
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                list.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return 1;
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        ViewHolder viewHolder;

                        if (convertView == null) {
                            // inflate the GridView item layout
                            LayoutInflater inflater = LayoutInflater.from(getActivity());
                            convertView =inflater.inflate(R.layout.message, parent, false);
                            //convertView = inflater.inflate(R.layout.job_detail, parent, false);

                            // initialize the view holder
                            viewHolder = new ViewHolder();
                            viewHolder.From = (TextView) convertView
                                    .findViewById(R.id.EditTextEmail);
                            viewHolder.subject = (TextView) convertView
                                    .findViewById(R.id.TextViewTitle);
                            viewHolder.details = (TextView) convertView
                                    .findViewById(R.id.EditTextRecivedMessage);
                            viewHolder.date = (TextView) convertView.findViewById(R.id.EditTextReciveddate);


                            convertView.setTag(viewHolder);
                        } else {
                            // recycle the already inflated view
                            viewHolder = (ViewHolder) convertView.getTag();
                        }

                        // update the item view
                        ParseQuery<App_User> user=  ParseQuery.getQuery(App_User.class);
                        try {
                            App_User temp= user.get( messages.get(position).getFromUser().getObjectId());
                            viewHolder.From.setText("From :"+ temp.getEmail());
                            viewHolder.subject.setText(messages.get(position).getTitle());
                            viewHolder.details.setText(messages.get(position).getContent());
                            viewHolder.date.setText(messages.get(position).getUpdatedAt().toGMTString());
                            current=messages.get(position);
                            btn =(Button)convertView.findViewById(R.id.ButtonSaveasDraft);


                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Messages temp =new Messages();
                                    temp.setTitle("Re:"+current.getTitle());
                                    feedback =(EditText)root.findViewById(R.id.EditTextFeedbackBody);
                                    temp.setContent(feedback.getText().toString());
                                    temp.setFromUser(current.getToUser());
                                    temp.setToUser(current.getFromUser());
                                    temp.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if(e!=null)
                                            {
                                                Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                                            }
                                            else
                                            {
                                                Toast.makeText(getActivity(),"sucess",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                            });

                            return convertView;

                        }
                        catch (Exception ex)
                        {
                            return null;
                        }




                    }
                });
            }
        });
        return root;


    }

    private static class ViewHolder {
        public TextView From ;
        public TextView subject;
        public TextView message;
        public TextView date ;
        public TextView details;

    }




}
