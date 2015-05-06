package it.polito.mobile.polijobplacement;

/**
 * Created by Admin on 5/6/2015.
 */


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.os.Bundle;

public class Company_ListViewAdapter extends ArrayAdapter<Company>  {

    public Company_ListViewAdapter(Context context, List<Company> items) {

        super(context, R.layout.companylistitem, items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.companylistitem, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.companyname = (TextView) convertView
                    .findViewById(R.id.Name);
            viewHolder.Sector = (TextView) convertView
                    .findViewById(R.id.Sector);
            viewHolder.Tittle = (TextView) convertView.findViewById(R.id.Tittle);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        Company item = getItem(position);
        viewHolder.companyname.setText(item.Name);
        viewHolder.Sector.setText(item.Sector);
        viewHolder.Tittle.setText(item.Tittle);

        return convertView;
    }
    private static class ViewHolder {
        public TextView Tittle;
        public TextView Sector;
        public TextView companyname;

    }

}