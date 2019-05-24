package com.pro.deepak.ieee_ex.data;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pro.deepak.ieee_ex.R;

import java.util.List;

import static java.security.AccessController.getContext;

public class adapterLV extends ArrayAdapter<dataElement> {

    public adapterLV(Context context, int resource, List<dataElement> objects)
    {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.model, parent, false);
        }

        TextView titleTV = convertView.findViewById(R.id.titleTV);
        TextView descriptionTV = convertView.findViewById(R.id.descTV);

        dataElement message = getItem(position);

        titleTV.setText(message.getTitle());
        descriptionTV.setText(message.getDesc());

        return convertView;
    }
}
