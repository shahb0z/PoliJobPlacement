package it.polito.mobile.polijobplacement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import  it.polito.mobile.polijobplacement.Data.Inbox;
import java.util.List;
import com.parse.ParseUser;
/**
 * Created by Admin on 5/9/2015.
 */
public class Inbox_ListViewAdapte_detail  extends ArrayAdapter<Inbox> {
    ParseUser user= ParseUser.getCurrentUser();
    public  Inbox_ListViewAdapte_detail(Context context, List<Inbox> items) {
        super(context, R.layout.message, items);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;



        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.message, parent, false);
            //convertView = inflater.inflate(R.layout.job_detail, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.EditTextEmail = (TextView) convertView
                    .findViewById(R.id.EditTextEmail);

            viewHolder.EditTextRecivedMessage = (TextView) convertView
                    .findViewById(R.id.EditTextRecivedMessage);
            viewHolder.TextViewTitle = (TextView) convertView.findViewById(R.id.TextViewTitle);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        Inbox item = getItem(position);

        viewHolder.EditTextEmail.setText(item.From);
       viewHolder.EditTextName.setText(user.getUsername());
       viewHolder.TextViewTitle.setText(item.subject);
        viewHolder.EditTextRecivedMessage.setText(item.message);

        return convertView;

    }
    private static class ViewHolder {

        public TextView EditTextName =null;
        public TextView TextViewTitle = null;
        public TextView subject= null;
        public TextView message= null;
        public TextView EditTextRecivedMessage = null;
        public TextView EditTextEmail = null;


    }
}
