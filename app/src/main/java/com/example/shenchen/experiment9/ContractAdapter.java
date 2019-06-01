package com.example.shenchen.experiment9;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ContractAdapter extends ArrayAdapter<Contract>{
    private int resourceId;

    public ContractAdapter(Context context, int textViewResourceId, List<Contract> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contract contract = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView contractName = (TextView)view.findViewById(R.id.name_text);
        TextView contractPhone = (TextView)view.findViewById(R.id.phone_text);
        contractName.setText(contract.getName());
        contractPhone.setText(contract.getPhone());
        return view;
    }

}
