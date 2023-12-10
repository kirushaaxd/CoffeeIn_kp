package com.example.coffeein_kp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterDishes extends RecyclerView.Adapter<AdapterDishes.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView dish_img;
        public TextView name;
        public Button price_btn;
        CardView dish_card;


        public ViewHolder(View itemView) {
            super(itemView);

            dish_img = (ImageView) itemView.findViewById(R.id.dish_img);
            name = (TextView) itemView.findViewById(R.id.name);
            price_btn = (Button) itemView.findViewById(R.id.price_btn);
            dish_card = (CardView) itemView.findViewById(R.id.dish_card);
        }
    }

    private List<Product> dishes;

    public AdapterDishes(List<Product> dishes) {
        this.dishes = dishes;
    }

    @Override
    public AdapterDishes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_dish, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterDishes.ViewHolder holder, int position) {
        Product dish = dishes.get(position);

        ImageView dish_img = (ImageView) holder.dish_img;
        Picasso.with(holder.dish_img.getContext()).load(dish.getImage())
                .error(R.drawable.app_small)
                .placeholder(R.drawable.app_small)
                .resize(250, 250)
                .into(dish_img);

        TextView dish_name = (TextView) holder.name;
        dish_name.setText(dish.getName());

        Button price_btn = (Button) holder.price_btn;
        price_btn.setText(String.valueOf(dish.getQuantity()));

        CardView dish_card = (CardView) holder.dish_card;
        dish_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDishInfo dishFragment = new FragmentDishInfo();
                dishFragment.TransferData(dish);
                dishFragment.show(StaticResources.fragmentManager, "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }
}