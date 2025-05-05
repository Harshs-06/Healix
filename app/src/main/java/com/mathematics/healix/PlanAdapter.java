package com.mathematics.healix;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {

    private List<PlanItem> planList;

    public PlanAdapter(List<PlanItem> planList) {
        this.planList = planList;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dietplans_itemlayout, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        PlanItem item = planList.get(position);
        holder.title.setText(item.getTitle());
        holder.image.setImageResource(item.getImageResId());
        holder.subtitle.setText(item.getSubtitle());
        holder.description.setText(item.getDescription());

        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(v.getContext(), week_diet_plan.class);
//            intent.putExtra("title",item.getTitle());
//            intent.putExtra("subtitle",item.getSubtitle());
//            v.getContext().startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return planList.size();
    }

    public static class PlanViewHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle, description;
        ImageView image;

        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.plantitle);
            subtitle = itemView.findViewById(R.id.plansubtitle);
            description = itemView.findViewById(R.id.plandescription);
            image = itemView.findViewById(R.id.planimg);
        }
    }
}
