package com.mathematics.healix;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class home_carousel_adapter extends RecyclerView.Adapter<home_carousel_adapter.ViewHolder> {

    private List<home_carousel_item> itemList;

    public home_carousel_adapter(List<home_carousel_item> itemList) {
        this.itemList = itemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView time, category, place, problem, detail;

        public ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            category = itemView.findViewById(R.id.category);
            place = itemView.findViewById(R.id.place);
            problem = itemView.findViewById(R.id.problem);
            detail = itemView.findViewById(R.id.detail);
        }
    }

    @NonNull
    @Override
    public home_carousel_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_carosuel_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull home_carousel_adapter.ViewHolder holder, int position) {
        home_carousel_item item = itemList.get(position);
        holder.time.setText(item.getTime());
        holder.category.setText(item.getCategory());
        holder.place.setText(item.getPlace());
        holder.problem.setText(item.getProblem());
        holder.detail.setText(item.getDetail());


        holder.itemView.setOnClickListener(v -> {

            Context context = v.getContext();

            Intent intent = new Intent(context, questions_answers_by_expert.class); // Replace with your new activity

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
