package it.polito.mobile.polijobplacement.Profile;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import it.polito.mobile.polijobplacement.R;


/**
 * Created by shahboz on 18/05/2015.
 */
public class ExpandableEntryAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater vi;
    private final ItemFragment parentF;
    private int lastExpandedGroupPosition;
    private ExpandableListView listView;

    public ExpandableEntryAdapter(Context context, ArrayList<Item> items, ItemFragment parent, ExpandableListView list){
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.parentF = parent;
        this.listView = list;
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(items.get(groupPosition).getType()==2)
            return 1;
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;

        final Item i = items.get(groupPosition);
        if (i != null) {
            switch (i.getType()) {
                case 0:
                    SectionItem si = (SectionItem) i;
                    v = vi.inflate(R.layout.list_item_section, null);

                    v.setOnClickListener(null);
                    v.setOnLongClickListener(null);
                    v.setLongClickable(false);

                    final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
                    sectionView.setText(si.getTitle());
                    break;
                case 1:
                    EntryItem ei = (EntryItem) i;
                    v = vi.inflate(R.layout.list_item_entry, null);
                    final TextView title = (TextView) v.findViewById(R.id.list_item_entry_title);
                    final TextView subtitle = (TextView) v.findViewById(R.id.list_item_entry_summary);
                    if(ei.entryType != 0){
                        ImageView im = (ImageView)v.findViewById(R.id.list_item_entry_drawable);
                        if(ei.entryType == 1){
                            im.setImageResource(R.drawable.ic_skill);
                        }
                        else{
                            im.setImageResource(R.drawable.ic_action_name);
                        }
                    }
                    if (title != null)
                        title.setText(ei.title);
                    if (subtitle != null)
                        subtitle.setText(ei.subtitle);
                    break;
                case 2:
                    ButtonItem bi = (ButtonItem) i;
                    v = vi.inflate(R.layout.list_item_button, null);
                    final TextView degreeButton = (TextView) v.findViewById(R.id.degree_add_text_view);
                    degreeButton.setText(bi.title);
                    break;

            }
        }
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;

            final Item i = items.get(groupPosition);
            if(i.hasChild()){
                int type = i.getType();
                if(type == 2){
                    //button item
                    ButtonItem bi = (ButtonItem) i;
                    if(bi.buttonType == 0){//degree entry type
                        //degree entry type
                        final int pos = groupPosition;
                        v = vi.inflate(R.layout.degree_list_add_item,null);
                        final EditText university = (EditText)v.findViewById(R.id.university_edit_text);

                        final Spinner degreeType = (Spinner)v.findViewById(R.id.data_degree_type_spinner);
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(parentF.getActivity(),
                                R.array.degree_type_array, android.R.layout.simple_spinner_item);
                        // Specify the layout to use when the list of choices appears
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // Apply the adapter to the spinner
                        degreeType.setAdapter(new NothingSelectedSpinnerAdapter(adapter,R.layout.contact_spinner_row_nothing_selected,parentF.getActivity()));

                        degreeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        final EditText major = (EditText)v.findViewById(R.id.major_edit_text);
                        final TextView startDateTextView = (TextView)v.findViewById(R.id.data_start_year);
                        startDateTextView.setOnClickListener(new View.OnClickListener() {
                            // @Override
                            public void onClick(View v) {
                                final Calendar c = Calendar.getInstance();

                                int y = c.get(Calendar.YEAR)-4;
                                int m = 8;
                                int d = 1;

                                DatePickerDialog dp = new DatePickerDialog(parentF.getActivity(),
                                        new DatePickerDialog.OnDateSetListener() {

                                            @Override
                                            public void onDateSet(DatePicker view, int year,
                                                                  int monthOfYear, int dayOfMonth) {
                                                c.set(year, monthOfYear, dayOfMonth);
                                                startDateTextView.setText(parentF.DATE_FORMATTER.format(c.getTime()));

                                            }

                                        }, y, m, d);
                                dp.setTitle("Calender");
                                dp.setMessage("Select Your Graduation date Please?");
                                dp.getDatePicker().findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
                                dp.show();



                            }
                        });

                        final TextView endDateTextView = (TextView)v.findViewById(R.id.data_end_year);
                        endDateTextView.setOnClickListener(new View.OnClickListener() {
                            // @Override
                            public void onClick(View v) {
                                final Calendar c = Calendar.getInstance();

                                int y = c.get(Calendar.YEAR)-4;
                                int m = 8;
                                int d = 1;



                                DatePickerDialog dp = new DatePickerDialog(parentF.getActivity(),
                                        new DatePickerDialog.OnDateSetListener() {

                                            @Override
                                            public void onDateSet(DatePicker view, int year,
                                                                  int monthOfYear, int dayOfMonth) {
                                                c.set(year, monthOfYear, dayOfMonth);
                                                endDateTextView.setText(parentF.DATE_FORMATTER.format(c.getTime()));

                                            }

                                        }, y, m, d);
                                dp.setTitle("Calender");
                                dp.setMessage("Select Your Graduation date Please?");
                                dp.getDatePicker().findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
                                dp.show();



                            }
                        });
                        Button b = (Button)v.findViewById(R.id.degree_add_button);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                parentF.addDegree(university.getText().toString(),degreeType.getSelectedItem().toString(), major.getText().toString(),startDateTextView.getText().toString(),endDateTextView.getText().toString(), pos);
                            }
                        });

                    }
                    else if(bi.buttonType == 1){
                        //skill entry type
                        final int pos = groupPosition;
                        v = vi.inflate(R.layout.skill_list_add_item,null);
                        Button b = (Button)v.findViewById(R.id.skill_add_button);
                        final EditText skill = (EditText)v.findViewById(R.id.skill_edit_text);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                parentF.addSkill(skill.getText().toString(), pos);
                            }
                        });
                    }
                    else{
                        //language skill type
                        v = vi.inflate(R.layout.language_list_add_item,null);
                        final int pos = groupPosition;
                        Button b = (Button)v.findViewById(R.id.language_add_button);
                        final EditText lang = (EditText)v.findViewById(R.id.language_edit_text);
                        final EditText lev = (EditText)v.findViewById(R.id.language_level_edit_text);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                parentF.addLang(lang.getText().toString(),lev.getText().toString(),pos);
                            }
                        });
                    }
                }
                else if(type == 1) {
                    //entry item
                    EntryItem ei = (EntryItem) i;
                    if(ei.entryType == 0){//degree entry type
                        final int pos = groupPosition;
                        v = vi.inflate(R.layout.degree_list_add_item,null);
                        final EditText university = (EditText)v.findViewById(R.id.university_edit_text);

                        final Spinner degreeType = (Spinner)v.findViewById(R.id.data_degree_type_spinner);
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(parentF.getActivity(),
                                R.array.degree_type_array, android.R.layout.simple_spinner_item);
                        // Specify the layout to use when the list of choices appears
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // Apply the adapter to the spinner
                        degreeType.setAdapter(new NothingSelectedSpinnerAdapter(adapter,R.layout.contact_spinner_row_nothing_selected,parentF.getActivity()));

                        degreeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        final EditText major = (EditText)v.findViewById(R.id.major_edit_text);
                        final TextView startDateTextView = (TextView)v.findViewById(R.id.data_start_year);
                        startDateTextView.setOnClickListener(new View.OnClickListener() {
                            // @Override
                            public void onClick(View v) {
                                final Calendar c = Calendar.getInstance();

                                int y = c.get(Calendar.YEAR)-4;
                                int m = 8;
                                int d = 1;

                                DatePickerDialog dp = new DatePickerDialog(parentF.getActivity(),
                                        new DatePickerDialog.OnDateSetListener() {

                                            @Override
                                            public void onDateSet(DatePicker view, int year,
                                                                  int monthOfYear, int dayOfMonth) {
                                                c.set(year, monthOfYear, dayOfMonth);
                                                startDateTextView.setText(parentF.DATE_FORMATTER.format(c.getTime()));

                                            }

                                        }, y, m, d);
                                dp.setTitle("Calender");
                                dp.setMessage("Select Your Graduation date Please?");
                                dp.getDatePicker().findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
                                dp.show();



                            }
                        });

                        final TextView endDateTextView = (TextView)v.findViewById(R.id.data_end_year);
                        endDateTextView.setOnClickListener(new View.OnClickListener() {
                            // @Override
                            public void onClick(View v) {
                                final Calendar c = Calendar.getInstance();

                                int y = c.get(Calendar.YEAR)-4;
                                int m = 8;
                                int d = 1;



                                DatePickerDialog dp = new DatePickerDialog(parentF.getActivity(),
                                        new DatePickerDialog.OnDateSetListener() {

                                            @Override
                                            public void onDateSet(DatePicker view, int year,
                                                                  int monthOfYear, int dayOfMonth) {
                                                c.set(year, monthOfYear, dayOfMonth);
                                                endDateTextView.setText(parentF.DATE_FORMATTER.format(c.getTime()));

                                            }

                                        }, y, m, d);
                                dp.setTitle("Calender");
                                dp.setMessage("Select Your Graduation date Please?");
                                dp.getDatePicker().findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
                                dp.show();



                            }
                        });
                        Button b = (Button)v.findViewById(R.id.degree_add_button);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                parentF.addDegree(university.getText().toString(),degreeType.getSelectedItem().toString(), major.getText().toString(),startDateTextView.getText().toString(),endDateTextView.getText().toString(), pos);
                            }
                        });

                    }
                    else if(ei.entryType == 1){
                        final int pos = groupPosition;
                        v = vi.inflate(R.layout.skill_list_add_item,null);
                        Button b = (Button)v.findViewById(R.id.skill_add_button);
                        final EditText skill = (EditText)v.findViewById(R.id.skill_edit_text);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                parentF.addSkill(skill.getText().toString(), pos);
                            }
                        });
                    }
                    else{
                        //language skill type
                        v = vi.inflate(R.layout.language_list_add_item,null);
                        final int pos = groupPosition;
                        Button b = (Button)v.findViewById(R.id.language_add_button);
                        final EditText lang = (EditText)v.findViewById(R.id.language_edit_text);
                        final EditText lev = (EditText)v.findViewById(R.id.language_level_edit_text);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                parentF.addLang(lang.getText().toString(),lev.getText().toString(),pos);
                            }
                        });
                    }
                }
            }

        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        if(groupPosition != lastExpandedGroupPosition){
            listView.collapseGroup(lastExpandedGroupPosition);
        }

        super.onGroupExpanded(groupPosition);
        lastExpandedGroupPosition = groupPosition;
    }
}
