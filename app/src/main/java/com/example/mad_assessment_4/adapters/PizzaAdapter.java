package com.example.mad_assessment_4.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.data.models.Pizza;
import com.example.mad_assessment_4.utils.Helper;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {
    private Context context;
    private List<Pizza> pizzas;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Pizza pizza);
    }

    public PizzaAdapter(Context context, List<Pizza> pizzas, OnDeleteClickListener onDeleteClickListener) {
        this.context = context;
        this.pizzas = pizzas;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzas.get(position);
        holder.textViewPizzaName.setText(pizza.getName());
        holder.textViewPizzaDescription.setText(pizza.getDescription());
        holder.textViewPizzaPrice.setText("Rs " + pizza.getPrice());
        Bitmap bitmap = Helper.getImageFromExternalStorage(pizza.getName());
        holder.imageViewPizza.setImageBitmap(bitmap);

        holder.buttonDelete.setOnClickListener(v -> onDeleteClickListener.onDeleteClick(pizza));
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPizzaName, textViewPizzaDescription, textViewPizzaPrice;
        ImageView imageViewPizza;
        Button buttonDelete;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPizzaName = itemView.findViewById(R.id.textViewPizzaName);
            textViewPizzaDescription = itemView.findViewById(R.id.textViewPizzaDescription);
            textViewPizzaPrice = itemView.findViewById(R.id.textViewPizzaPrice);
            imageViewPizza = itemView.findViewById(R.id.imageViewPizza);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
