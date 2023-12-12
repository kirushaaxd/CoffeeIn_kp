package com.example.coffeein_kp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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

    private List<Product> order_story_items;

    public AdapterOrdersStoryItem(List<Product> order_story_items) {
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
        Product dish = order_story_items.get(position);

        ImageView dish_img = (ImageView) holder.dish_img;

        TextView name = (TextView) holder.name;

        TextView price = (TextView) holder.price;

    }

    @Override
    public int getItemCount() {
        return order_story_items.size();
    }
}