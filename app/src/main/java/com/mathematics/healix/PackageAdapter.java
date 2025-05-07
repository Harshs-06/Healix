package com.mathematics.healix;

import android.content.Context;
import android.content.Intent;
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

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageViewHolder> {

    List<PackageItem> itemList;
    Context context;

    public PackageAdapter(Context context, List<PackageItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.labtest_searchedpackages_recyclerlayout, parent, false);
        return new PackageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageViewHolder holder, int position) {
        PackageItem item = itemList.get(position);

        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDesc());
        holder.noOfTests.setText(item.getNoOfTests());
        holder.tat.setText(item.getTat());
        holder.actualPrice.setText(item.getActualPrice());
        holder.totalPrice.setText(item.getTotalPrice());
        holder.discount.setText(item.getDiscount());

        holder.bookbutton.setOnClickListener(v -> {
            Intent intent = new Intent(context , book_tests.class);
            intent.putExtra("title",item.getTitle());
            intent.putExtra("desc",item.getDesc());
            intent.putExtra("price",item.getActualPrice());

            context.startActivity(intent);

        });

        holder.showallbutton.setOnClickListener(v -> {
            Intent intent = new Intent(context , healthcheckup_seemore.class);
            intent.putExtra("title",item.getTitle());
            intent.putExtra("desc",item.getDesc());
//            intent.putExtra("price",item.getActualPrice());

            context.startActivity(intent);

        });


        

        holder.favBtn.setOnClickListener(v -> {
            boolean isSelected = holder.favBtn.isSelected();
            holder.favBtn.setSelected(!isSelected);
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

    class PackageViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc, noOfTests, tat, actualPrice, totalPrice, discount;
        ImageButton favBtn;
        MaterialButton bookbutton,showallbutton;

        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.packagetitle);
            desc = itemView.findViewById(R.id.packagedesc);
            noOfTests = itemView.findViewById(R.id.notest);
            tat = itemView.findViewById(R.id.testtime);
            actualPrice = itemView.findViewById(R.id.actualprice);
            totalPrice = itemView.findViewById(R.id.totalprice);
            discount = itemView.findViewById(R.id.discount);
            favBtn = itemView.findViewById(R.id.favourite);
            bookbutton = itemView.findViewById(R.id.bookbutton);
            showallbutton = itemView.findViewById(R.id.showallbutton);

        }
    }
}
