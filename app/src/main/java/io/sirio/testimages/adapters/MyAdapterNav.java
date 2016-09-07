package io.sirio.testimages.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import io.sirio.testimages.R;

/**
 * Created by Produccion on 30/6/2016.
 */

public class MyAdapterNav extends BaseAdapter {
    String[] itemNameNav;
    Context mContext;

    public MyAdapterNav(Context mContext) {
        itemNameNav = mContext.getResources().getStringArray(R.array.item_nav);
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return itemNameNav.length;
    }

    @Override
    public Object getItem(int position) {
        return itemNameNav[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = null;
        TextView txtItemName;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = inflater.inflate(R.layout.custom_row_nav, parent, false);

        }else{
            root = convertView;
        }
        txtItemName = (TextView) root.findViewById(R.id.txt_item_nav);
        txtItemName.setText(itemNameNav[position]);


        return root;
    }
}
