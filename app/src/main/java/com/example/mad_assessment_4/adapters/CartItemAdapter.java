package com.example.mad_assessment_4.adapters;

// CartItemAdapter.java

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.data.models.CartItem;
import com.example.mad_assessment_4.data.models.Pizza;
import com.example.mad_assessment_4.utils.CartManager;
import com.example.mad_assessment_4.utils.Helper;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {

    private List<CartItem> cartItems;

    public CartItemAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        // Bind data to views in ViewHolder
        holder.textViewName.setText(cartItem.getPizza().getName() );
        holder.textViewPrice.setText(String.valueOf(cartItem.getPizza().getPrice())+ " X");
        holder.textViewCartItemQuantityValue.setText(String.valueOf(cartItem.getQuantity())+ " =");
        holder.tvTotal.setText(String.valueOf(cartItem.getPizza().getPrice()*cartItem.getQuantity()));
        Bitmap bitmap = Helper.getImageFromExternalStorage(cartItem.getPizza().getName());
        holder.imItem.setImageBitmap(bitmap);

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartManager.getInstance().removeFromCart(cartItem.getPizza());
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartItems.size());
            }
        });

        // Set other data as needed
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewPrice, textViewCartItemQuantityValue,tvTotal;
        ImageView imItem;
        Button btnRemove;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.tvItemName);
            textViewPrice = itemView.findViewById(R.id.tvPrice);
            textViewCartItemQuantityValue = itemView.findViewById(R.id.tvQuantity);
            btnRemove = itemView.findViewById(R.id.buttonRemoveCartItem);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            imItem = itemView.findViewById(R.id.ivItem);
        }
    }

}
