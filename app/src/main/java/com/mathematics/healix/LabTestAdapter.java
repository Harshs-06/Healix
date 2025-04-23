package com.mathematics.healix;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class LabTestAdapter extends RecyclerView.Adapter<LabTestAdapter.LabTestViewHolder> {

    private Context context;
    private List<LabTestItem> labTestList;

    public LabTestAdapter(Context context, List<LabTestItem> labTestList) {
        this.context = context;
        this.labTestList = labTestList;
    }

    @NonNull
    @Override
    public LabTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.labtest_itemlayout, parent, false);
        return new LabTestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LabTestViewHolder holder, int position) {
        LabTestItem item = labTestList.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.gender.setText(item.getGender());
        holder.ageGroup.setText(item.getAgeGroup());
        holder.testTime.setText(item.getTestTime());
        holder.actualPrice.setText(item.getActualPrice());
        holder.totalPrice.setText(item.getTotalPrice());
        holder.discount.setText(item.getDiscount());
        holder.bookButton.setOnClickListener(v -> {
            // Add your booking logic here
        });

        holder.favourite4.setOnClickListener(v -> {
            boolean isSelected = holder.favourite4.isSelected();
            holder.favourite4.setSelected(!isSelected);
            if(!isSelected){
                Toast.makeText(v.getContext(),"Marked as favourite",Toast.LENGTH_SHORT).show();
            }
        });

        String totalPriceText = item.getTotalPrice();
        SpannableString spannableString = new SpannableString(totalPriceText);
        spannableString.setSpan(new StrikethroughSpan(), 0, totalPriceText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.totalPrice.setText(spannableString);
    }

    @Override
    public int getItemCount() {
        return labTestList.size();
    }

    public static class LabTestViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, gender, ageGroup, testTime, actualPrice, totalPrice, discount;
        MaterialButton bookButton;
        ImageButton favourite4;

        public LabTestViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.packagetitle3);
            description = itemView.findViewById(R.id.packagedesc3);
            gender = itemView.findViewById(R.id.gender);
            ageGroup = itemView.findViewById(R.id.agegroup);
            testTime = itemView.findViewById(R.id.testtime3);
            actualPrice = itemView.findViewById(R.id.actualprice3);
            totalPrice = itemView.findViewById(R.id.totalprice3);
            discount = itemView.findViewById(R.id.discount3);
            bookButton = itemView.findViewById(R.id.booklabtest);
            favourite4 = itemView.findViewById(R.id.favourite4);
        }
    }
}
