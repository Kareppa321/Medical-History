package com.example.medicalhistory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.DiseaseViewHolder> {

    private ArrayList<DiseaseEntry> data;
    public DiseaseAdapter(ArrayList<DiseaseEntry> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.disease_list_item, parent, false);
        return new DiseaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseViewHolder holder, int position) {
        holder.date.setText("Date: " + data.get(position).date);
        holder.diseaseName.setText("Disease Name: " + data.get(position).diseaseName);
        holder.doctorName.setText("Doctors Name: " + data.get(position).doctorName);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MoreDetails.class);
                intent.putExtra("date", data.get(position).date);
                intent.putExtra("disease", data.get(position).diseaseName);
                intent.putExtra("doctor", data.get(position).doctorName);
                intent.putExtra("hospital", data.get(position).hospitalName);
                intent.putExtra("address", data.get(position).hospitalAddress);
                intent.putExtra("medicine", data.get(position).medicineName);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DiseaseViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView diseaseName;
        TextView doctorName;
        CardView cardView;


        public DiseaseViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            diseaseName = itemView.findViewById(R.id.diseaseName);
            doctorName = itemView.findViewById(R.id.doctorName);
            cardView = itemView.findViewById(R.id.singleItem);

        }
    }

}
