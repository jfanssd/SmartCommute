package com.example.compuhypermeganet.smart_commute.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.compuhypermeganet.smart_commute.MainActivity;
import com.example.compuhypermeganet.smart_commute.R;
import com.example.compuhypermeganet.smart_commute.model.Leg;
import com.example.compuhypermeganet.smart_commute.model.Trip;

import java.util.List;

public class TripAdapter extends BaseAdapter {
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    private List<Leg> legList;
    public Trip trip;

    public TripAdapter(Context context, int resource, Trip trip){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        this.legList = trip.getLegs();
        this.trip = trip;
    }
    @Override
    public int getCount() { return legList == null ? 0:legList.size();}

    @Override
    public Leg getItem(int position) {return legList == null? null:legList.get(position);}

    @Override
    public long getItemId(int position) { return position;}

    public static class ViewHolder {
        TextView depart_time;
        TextView from;
        TextView mode;
        TextView arriv_time;
        TextView arriv_address;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TripAdapter.ViewHolder vh = null;
        View view;
        Leg leg = getItem(position);
        if (leg != null) {
            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
                vh = new TripAdapter.ViewHolder();
                vh.arriv_address = (TextView) convertView.findViewById(R.id.arriv_address);
                vh.arriv_time = (TextView) convertView.findViewById(R.id.arriv_time);
                vh.depart_time = (TextView) convertView.findViewById(R.id.depart_time);
                vh.from = (TextView) convertView.findViewById(R.id.from);
                vh.mode = (TextView) convertView.findViewById(R.id.mode);
                Log.d("position",position+"=="+legList.size());
                convertView.setTag(vh);
            } else {
                vh = (TripAdapter.ViewHolder) convertView.getTag();

            }
                vh.depart_time.setText(leg.getDeparture().toString().substring(11, 16));
                vh.arriv_time.setText(leg.getArrival().toString().substring(11, 16));
                vh.arriv_address.setText(leg.getTo().getName());
                vh.from.setText(leg.getFrom().getName());
            ImageView icons = convertView.findViewById(R.id.icons);
            if (leg.getLine() == null) {
                    vh.mode.setText(leg.getMode());
                    icons.setImageResource(R.drawable.walk);
                } else {
                    vh.mode.setText(leg.getLine() + " " + leg.getDirection());
                    if(leg.getLine().contains("U")){
                        icons.setImageResource(R.drawable.ubahn);
                    }else if(leg.getLine().contains("S")){
                        icons.setImageResource(R.drawable.sbahn);
                    }else{
                        icons.setImageResource(R.drawable.train);
                    }
                }
                Log.d("line", leg.getLine() + " " + leg.getDirection() + " " + leg.getMode());
        }





            return convertView;
        }
    }


