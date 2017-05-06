package com.example.anderson.webcoleta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anderson.webcoleta.R;
import com.example.anderson.webcoleta.model.GarbagePlace;

/**
 * Created by Anderson on 05/04/2017.
 */

public class GarbagePlacesAdapter extends BaseAdapter {


    private Context mContext;
    private GarbagePlace[] mGarbagePlace;

    public GarbagePlacesAdapter(Context mContext, GarbagePlace[] mGarbagePlace)
    {
        this.mGarbagePlace=mGarbagePlace;
        this.mContext = mContext;

    }

    @Override
    public int getCount(){return (mGarbagePlace != null)? mGarbagePlace.length: 0;}

    @Override
    public View getView(int i, View v, ViewGroup vg)
    {
        GarbagePlace garbagePlace = mGarbagePlace[i];

        ViewHolder holder = null;

        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.item_garbage_place, null);

            holder = new ViewHolder();
            holder.imgLogo = (ImageView) v.findViewById(R.id.imgLogo);
            holder.txtPlaceName = (TextView) v.findViewById(R.id.txtGarbagePlaceName);

            v.setTag(holder);

        }else{
            holder = (ViewHolder)v.getTag();
        }

        holder.imgLogo.setImageResource(R.drawable.garbage_icon);
        holder.txtPlaceName.setText(garbagePlace.getStreet());

        return v;

    }

    @Override
    public Object getItem(int id)
    {
        return mGarbagePlace[id];

    }

    @Override
    public long getItemId(int i)
    {

        return Long.parseLong(mGarbagePlace[i].getId());
    }

    static class ViewHolder {
        ImageView imgLogo;
        TextView txtPlaceName;
    }

}
