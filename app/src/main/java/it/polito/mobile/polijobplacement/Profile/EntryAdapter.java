package it.polito.mobile.polijobplacement.Profile;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import it.polito.mobile.polijobplacement.R;


public class EntryAdapter extends ArrayAdapter<Item> {

	private Context context;
	private ArrayList<Item> items;
	private LayoutInflater vi;

	public EntryAdapter(Context context,ArrayList<Item> items) {
		super(context,0, items);
		this.context = context;
		this.items = items;
		vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		final Item i = items.get(position);
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
					TextView degreeButton = (TextView) v.findViewById(R.id.degree_add_button);
					degreeButton.setText(bi.title);
					break;

			}
		}
		return v;
	}

}
