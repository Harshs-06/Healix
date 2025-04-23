package com.mathematics.healix;

import android.content.Context;
import android.graphics.Paint;
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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HealthCheckupAdapter extends RecyclerView.Adapter<HealthCheckupAdapter.ViewHolder> {

    private Context context;
    private List<HealthCheckupItem> itemList;

    public HealthCheckupAdapter(Context context, List<HealthCheckupItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.healthcheckups_listlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthCheckupItem item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.testCount.setText(item.getTestCount());
        holder.tat.setText(item.getTat());
        holder.actualPrice.setText(item.getActualPrice());
        holder.totalPrice.setText(item.getTotalPrice());
        holder.totalPrice.setPaintFlags(holder.totalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.discount.setText(item.getDiscount());


        holder.favourite2.setOnClickListener(v -> {
            boolean isSelected = holder.favourite2.isSelected();
            holder.favourite2.setSelected(!isSelected);
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
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, testCount, tat, actualPrice, totalPrice, discount;
        ImageButton favourite2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.packagetitle2);
            description = itemView.findViewById(R.id.packagedesc2);
            testCount = itemView.findViewById(R.id.notes2t);
            tat = itemView.findViewById(R.id.testtime2);
            actualPrice = itemView.findViewById(R.id.actualprice2);
            totalPrice = itemView.findViewById(R.id.totalprice2);
            discount = itemView.findViewById(R.id.discount2);
            favourite2 = itemView.findViewById(R.id.favourite2);
        }
    }
}

