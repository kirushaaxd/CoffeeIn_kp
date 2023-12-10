package com.example.coffeein_kp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterCoffeeHouse extends RecyclerView.Adapter<AdapterCoffeeHouse.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView coffeeHouseImg;
        public TextView openTime;
        public TextView name;
        public TextView location;



        public ViewHolder(View itemView) {
            super(itemView);

            coffeeHouseImg = (ImageView) itemView.findViewById(R.id.coffee_house_img);
            openTime = (TextView) itemView.findViewById(R.id.open_time);
            name = (TextView) itemView.findViewById(R.id.name);
            location = (TextView) itemView.findViewById(R.id.location);

        }
    }

    private List<CoffeeHouse> coffeeHouses;

    public AdapterCoffeeHouse(List<CoffeeHouse> coffeeHouses) {
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
        CoffeeHouse coffeeHouse = coffeeHouses.get(position);

        ImageView coffeeHouseImg = (ImageView) holder.coffeeHouseImg;

        TextView openTime = (TextView) holder.openTime;
        openTime.setText("Работает c " + coffeeHouse.getTimeOpen() + " до " + coffeeHouse.getTimeClose());

        TextView name = (TextView) holder.name;
        name.setText(coffeeHouse.getName());

        TextView location = (TextView) holder.location;
        location.setText(coffeeHouse.getAddress());
    }

    @Override
    public int getItemCount() {
        return coffeeHouses.size();
    }
}