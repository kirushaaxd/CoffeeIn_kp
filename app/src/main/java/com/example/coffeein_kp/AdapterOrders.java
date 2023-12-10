package com.example.coffeein_kp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class AdapterOrders extends RecyclerView.Adapter<AdapterOrders.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView dish_img;
        public TextView name;
        public Button col_minus;
        public TextView price;
        public Button col_plus;



        public ViewHolder(View itemView) {
            super(itemView);

            dish_img = (ImageView) itemView.findViewById(R.id.dish_img);
            name = (TextView) itemView.findViewById(R.id.name);
            col_minus = (Button) itemView.findViewById(R.id.col_minus);
            price = (TextView) itemView.findViewById(R.id.price);
            col_plus = (Button) itemView.findViewById(R.id.col_plus);

        }
    }

    private LinkedHashMap<Product, Integer> orders;

    public AdapterOrders(LinkedHashMap<Product, Integer> orders) {
        this.orders = orders;
    }

    @Override
    public AdapterOrders.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_order, parent, false);

        AdapterOrders.ViewHolder viewHolder = new AdapterOrders.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterOrders.ViewHolder holder, int position) {
        Product dish = (Product) orders.keySet().toArray()[position];
        final int[] count = {orders.get(dish)};

        ImageView dish_img = (ImageView) holder.dish_img;
        Picasso.with(holder.dish_img.getContext()).load(dish.getImage())
                .error(R.drawable.app_small)
                .placeholder(R.drawable.app_small)
                .resize(250, 250)
                .into(dish_img);

        TextView name = (TextView) holder.name;
        name.setText(dish.getName());

        Button col_minus = (Button) holder.col_minus;

        TextView countT = (TextView) holder.price;
        countT.setText(count[0] + "");

        Button col_plus = (Button) holder.col_plus;

        col_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count[0] > 1){
                    countT.setText((--count[0]) + "");
                    StaticResources.ordersList.put(dish, count[0]);
                }
                else{
                    StaticResources.ordersList.remove(dish);
                    Toast.makeText(countT.getContext(), "Товар удален из корзины", Toast.LENGTH_SHORT).show();
                }
                MainActivityApp.fragmentOrder.updateRecycler();
            }
        });

        col_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count[0] < 10){
                    countT.setText((++count[0]) + "");
                    StaticResources.ordersList.put(dish, count[0]);
                    MainActivityApp.fragmentOrder.updateRecycler();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}