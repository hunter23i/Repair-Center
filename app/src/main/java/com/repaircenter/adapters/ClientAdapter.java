package com.repaircenter.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.repaircenter.Client_Details;
import com.repaircenter.R;
import com.repaircenter.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.MyViewHolder>
{
    Context context;
    Activity activity;
    ArrayList<String> barcode, name, phoneNumber;
    int position;
    ArrayList<Client> ClientArrayList;
    List<Client> modelList ;
    List<Client> modelListFilter ;


    public ClientAdapter(Activity activity, Context context, ArrayList<String> barcode, ArrayList<String> name, ArrayList<String> phoneNumber) {
        this.context = context;
        this.barcode = barcode;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.activity = activity;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.clientcustomlistview, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;
        holder.barcode.setText(barcode.get(position));
        holder.name.setText(name.get(position));
        holder.phoneNumber.setText(phoneNumber.get(position));
        try {
            holder.clientCustomLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Client_Details.class);
                    intent.putExtra("barcode", barcode.get(position));
                    intent.putExtra("name", name.get(position));
                    intent.putExtra("phone number", phoneNumber.get(position));
                    try {
                        activity.startActivityForResult(intent, 1);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return barcode.size();
    }







    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView barcode , name, phoneNumber;
        LinearLayout clientCustomLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            barcode = itemView.findViewById(R.id.barcode_text2);
            name = itemView.findViewById(R.id.name_text2);
            phoneNumber = itemView.findViewById(R.id.phone_number_text);

            clientCustomLayout = itemView.findViewById(R.id.clientCustomRow);
        }
    }
}
