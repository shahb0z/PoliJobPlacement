package it.polito.mobile.polijobplacement;

/**
 * Created by Admin on 5/6/2015.
 */


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import it.polito.mobile.polijobplacement.Data.Company_2;

public class ListViewAdapter_detail extends ArrayAdapter<Company_2>  {

    Button cancel;


    public ListViewAdapter_detail(Context context, List<Company_2> items) {
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
        Company_2 item = getItem(position);
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