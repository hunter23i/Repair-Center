package com.repaircenter.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.repaircenter.Phone_Details;
import com.repaircenter.R;

import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> implements Filterable {
    Context context;
    Activity activity;
    ArrayList<String> barcode,phonemodel,imei,description,status;
   int position;

    public PhoneAdapter(Activity activity,Context context,ArrayList<String> barcode , ArrayList<String> phonemodel , ArrayList<String> imei , ArrayList<String> description , ArrayList<String> status)
    {
        this.activity = activity;
        this.context = context;
        this.barcode = barcode;
        this.phonemodel = phonemodel;
        this.imei = imei;
        this.description = description;
        this.status = status;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.phonecustomlistview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position=position;
        holder.barcode.setText(barcode.get(position));
        holder.phonemodel.setText(phonemodel.get(position));
        holder.imei.setText(imei.get(position));
        holder.descr.setText(description.get(position));
        holder.status.setText(status.get(position));
        try {
            holder.phoneCustomLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Phone_Details.class);
                    intent.putExtra("barcode", barcode.get(position));
                    intent.putExtra("phone model", phonemodel.get(position));
                    intent.putExtra("imei", imei.get(position));
                    intent.putExtra("description", description.get(position));
                    intent.putExtra("status", status.get(position));
                    try {
                        activity.startActivityForResult(intent, 1);
                    }catch (Exception e){
                        e.getMessage();
                    }
                }
            });
        } catch(Exception e) {
            e.getMessage();
        }
    }

    @Override
    public int getItemCount() {
            return barcode.size();
    }

     @Override







  public Filter getFilter() {
          Filter filter = new Filter() {
              @Override
              protected FilterResults performFiltering(CharSequence constraint) {
                  FilterResults filterResults = new FilterResults();
                 if(constraint == null || constraint.length() == 0){
                      filterResults.count = barcode.size();
                      filterResults.values = barcode;
                  }else {
                      String searchStr = constraint.toString().toLowerCase();
                      ArrayList<ArrayList<String>> resultData = new ArrayList<>();

                          if (barcode.contains(searchStr) || phonemodel.contains(searchStr) || imei.contains(searchStr)){
                              resultData.add(barcode);

                          filterResults.count = resultData.size();
                          filterResults.values = resultData;


                      }
                  }
                  return filterResults;
              }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
              notifyDataSetChanged();
            }
        };

        return filter;
    }











    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView barcode , phonemodel, imei, descr, status ;
        LinearLayout phoneCustomLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            barcode = itemView.findViewById(R.id.barcode_text);
            phonemodel = itemView.findViewById(R.id.phmodel_text);
            imei = itemView.findViewById(R.id.imei_text);
            descr = itemView.findViewById(R.id.description_text);
            status = itemView.findViewById(R.id.status_text);
            phoneCustomLayout = itemView.findViewById(R.id.phonelayout);
        }
    }
}
