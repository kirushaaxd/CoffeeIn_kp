package com.example.coffeein_kp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterOrdersStoryItem extends RecyclerView.Adapter<AdapterOrdersStoryItem.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView dish_img;

        public TextView name;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);

            dish_img = (ImageView) itemView.findViewById(R.id.dish_img);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);

        }
    }

    private List<OrderProduct> order_story_items;

    public AdapterOrdersStoryItem(List<OrderProduct> order_story_items) {
        this.order_story_items = order_story_items;
    }

    @Override
    public AdapterOrdersStoryItem.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_order_story_small, parent, false);

        AdapterOrdersStoryItem.ViewHolder viewHolder = new AdapterOrdersStoryItem.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterOrdersStoryItem.ViewHolder holder, int position) {
        OrderProduct dish = order_story_items.get(position);

        ImageView dish_img = (ImageView) holder.dish_img;
        Picasso.with(dish_img.getContext()).load(dish.getProduct().getImage())
                .error(R.drawable.app_small)
                .placeholder(R.drawable.app_small)
                .resize(250, 250)
                .into(dish_img);


        TextView name = (TextView) holder.name;
        name.setText(dish.getProduct().getName());

        TextView price = (TextView) holder.price;
        price.setText(dish.getCount() + " x " + dish.getPrice());

    }

    @Override
    public int getItemCount() {
        return order_story_items.size();
    }
}