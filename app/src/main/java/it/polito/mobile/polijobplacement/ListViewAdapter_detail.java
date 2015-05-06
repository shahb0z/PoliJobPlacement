package it.polito.mobile.polijobplacement;

/**
 * Created by Admin on 5/6/2015.
 */


import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter_detail extends ArrayAdapter<Company>  {

    Button cancel;


    public ListViewAdapter_detail(Context context, List<Company> items) {
        super(context, R.layout.mylistitem_detail, items);
		/*cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						StatisticsActivity.class);
				startActivity(intent);


				    //

			}
		});*/
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mylistitem_detail, parent, false);

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
        viewHolder.Tittle.setText(item.Tittle);
        viewHolder.Sector.setText(item.Sector);
        viewHolder.companyname.setText(item.Name);
        viewHolder.anyinfo.setText("Additionla info");

        return convertView;

    }


    private static class ViewHolder {
        public TextView Tittle;
        public TextView Sector;
        public TextView companyname;
        public TextView  anyinfo;

    }



}