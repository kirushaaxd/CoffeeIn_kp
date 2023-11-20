package com.example.coffeein_kp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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

    private List<Object> orders;

    public AdapterOrders(List<Object> orders) {
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
        Object dish = orders.get(position);


        ImageView dish_img = (ImageView) holder.dish_img;

        TextView name = (TextView) holder.name;

        Button col_minus = (Button) holder.col_minus;

        TextView price = (TextView) holder.price;

        Button col_plus = (Button) holder.col_plus;


        col_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(price.getText().toString());
                if (n > 1){
                    price.setText((n - 1) + "");
                }
            }
        });

        col_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(price.getText().toString());
                if (n < 10){
                    price.setText((n + 1) + "");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}