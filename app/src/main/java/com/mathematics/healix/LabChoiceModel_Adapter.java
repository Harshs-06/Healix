package com.mathematics.healix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LabChoiceModel_Adapter extends RecyclerView.Adapter<LabChoiceModel_Adapter.LabChoiceModel_ViewHolder> {
    List<LabChoice_Model> choices;

    public interface OnItemClickListener {
        void onItemClick(LabChoice_Model item);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public LabChoiceModel_Adapter(List<LabChoice_Model> choices) {
        this.choices = choices;
    }

    @NonNull
    @Override
    public LabChoiceModel_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.labtest_choices_recyclerlayout, parent, false);
        return new LabChoiceModel_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LabChoiceModel_ViewHolder holder, int position) {
        LabChoice_Model choice = choices.get(position);
        holder.imageView.setImageResource(choice.getImageResId());
        holder.titleView.setText(choice.getTitle());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(choice);
            }
        });
    }

    @Override
    public int getItemCount() {
        return choices.size();
    }

    static class LabChoiceModel_ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView;

        public LabChoiceModel_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.choices_icon);
            titleView = itemView.findViewById(R.id.choices_text);
        }
    }
}
