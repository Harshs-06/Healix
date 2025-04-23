package com.mathematics.healix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoctorAnswerAdapter extends RecyclerView.Adapter<DoctorAnswerAdapter.DoctorViewHolder> {

    private List<DoctorAnswer> doctorList;
    private Context context;

    public DoctorAnswerAdapter(Context context, List<DoctorAnswer> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doctor_answers_layout, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        DoctorAnswer item = doctorList.get(position);
        holder.name.setText(item.getDoctorName());
        holder.specialist.setText(item.getDoctorSpecialist());
        holder.time.setText(item.getResponseTime());
        holder.suggestion.setText(item.getSuggestion());

        holder.thumbsup.setOnClickListener(v -> {
            boolean isSelected = holder.thumbsup.isSelected();
            holder.thumbsup.setSelected(!isSelected);
            if(!isSelected){
                Toast.makeText(v.getContext(),"Liked the answer",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    static class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView name, specialist, time, suggestion;
        ImageButton thumbsup;

        DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.doctorname);
            specialist = itemView.findViewById(R.id.doctorspecialist);
            time = itemView.findViewById(R.id.doctorresponsetime);
            suggestion = itemView.findViewById(R.id.doctorsuggestion);
            thumbsup = itemView.findViewById(R.id.thumbsup);
        }
    }
}
