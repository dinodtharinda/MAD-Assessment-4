package com.example.mad_assessment_4.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.data.models.Pizza;
import com.example.mad_assessment_4.utils.Helper;
import com.example.mad_assessment_4.views.PizzaDetailsActivity;

import java.util.List;

public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.GridViewHolder> {

    private Context context;
    private List<Pizza> dataList;

    public MyGridAdapter(Context context, List<Pizza> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item_layout, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Pizza data = dataList.get(position);
        // Bind data to views
        holder.textViewName.setText(data.getName());
        holder.tvPrice.setText(String.valueOf(data.getPrice()));
       holder.imageView.setImageBitmap(Helper.getImageFromExternalStorage(data.getName()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PizzaDetailsActivity.class);
            intent.putExtra("pizza_id", data.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName,tvPrice;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
