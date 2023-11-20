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

public class AdapterCoffeeHouse extends RecyclerView.Adapter<AdapterCoffeeHouse.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView coffee_house_img;
        public TextView open_time;
        public TextView name;
        public TextView location;



        public ViewHolder(View itemView) {
            super(itemView);

            coffee_house_img = (ImageView) itemView.findViewById(R.id.coffee_house_img);
            open_time = (TextView) itemView.findViewById(R.id.open_time);
            name = (TextView) itemView.findViewById(R.id.name);
            location = (TextView) itemView.findViewById(R.id.location);

        }
    }

    private List<Object> coffeeHouses;

    public AdapterCoffeeHouse(List<Object> coffeeHouses) {
        this.coffeeHouses = coffeeHouses;
    }

    @Override
    public AdapterCoffeeHouse.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_coffee_house, parent, false);

        AdapterCoffeeHouse.ViewHolder viewHolder = new AdapterCoffeeHouse.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterCoffeeHouse.ViewHolder holder, int position) {
        Object coffee_house = coffeeHouses.get(position);

        ImageView coffee_house_img = (ImageView) holder.coffee_house_img;

        TextView open_time = (TextView) holder.open_time;

        TextView name = (TextView) holder.name;

        TextView location = (TextView) holder.location;

    }

    @Override
    public int getItemCount() {
        return coffeeHouses.size();
    }
}