package com.mathematics.healix;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ScheduledTestAdapter extends RecyclerView.Adapter<ScheduledTestAdapter.ViewHolder> {

    private Context context;
    private List<ScheduledTestModel> testList;

    public ScheduledTestAdapter(Context context, List<ScheduledTestModel> testList) {
        this.testList = testList;
        this.context = context;
        Log.d("test", "Constructor");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("test", "hi");
        View view = LayoutInflater.from(context).inflate(R.layout.item_scheduled_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("test", "hello");
        ScheduledTestModel model = testList.get(position);

        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.category.setText(String.format("Category: %s", model.getCategory()));
        holder.dateTime.setText(String.format("%s at %s", model.getDate(), model.getTime()));
        holder.address.setText(String.format("%s - %s", model.getAddress(), model.getPincode()));
        holder.amount.setText(String.format("%s", model.getAmount()));
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, category, dateTime, address, amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.testTitle);
            description = itemView.findViewById(R.id.testDescription);
            category = itemView.findViewById(R.id.testCategory);
            dateTime = itemView.findViewById(R.id.testDateTime);
            address = itemView.findViewById(R.id.testAddress);
            amount = itemView.findViewById(R.id.testAmount);
        }
    }
}
