package com.example.android_practice5_listviewforshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    ArrayList<Item> itemList = new ArrayList<>();
    //we need this object to link between this java class and the list_row XML file
    private LayoutInflater layoutInflater;

    public ItemAdapter(Context context, ArrayList<Item> itemList){
        this.itemList = itemList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView =layoutInflater.inflate(R.layout.list_layout,null);

            viewHolder = new ViewHolder();

            viewHolder.tvItemName = convertView.findViewById(R.id.tvItemName);
            viewHolder.tvPrice = convertView.findViewById(R.id.tvPrice);
            viewHolder.imgItem = convertView.findViewById(R.id.imgItem);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvItemName.setText(itemList.get(position).getName());
        viewHolder.tvPrice.setText(String.valueOf(itemList.get(position).getPrice()));

        int imgId = convertView.getResources().getIdentifier(itemList.get(position).getImg(), "mipmap", layoutInflater.getContext().getPackageName());
        viewHolder.imgItem.setImageResource(imgId);

        return convertView;
    }

    private class ViewHolder{
        //create attributes according to the object related to the list_layout
        private TextView tvItemName;
        private TextView tvPrice;
        private ImageView imgItem;
    }

}
