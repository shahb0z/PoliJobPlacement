package it.polito.mobile.polijobplacement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 5/7/2015.
 */
public class Inbox_ListViewAdapter extends ArrayAdapter<Inbox>
{ public  Inbox_ListViewAdapter(Context context, List<Inbox> items) {

    super(context, R.layout.inbox_list, items);
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.inbox_list_item, parent, false);

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
        Inbox item = getItem(position);
        viewHolder.From.setText(item.From);
        viewHolder.subject.setText(item.subject);
        viewHolder.date.setText(item.date);

        return convertView;
    }
    private static class ViewHolder {
        public TextView From = null;
        public TextView subject= null;
        public TextView message= null;
        public TextView date = null;

    }

}
