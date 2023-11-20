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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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

    private List<Object> dishes;

    public AdapterDishes(List<Object> dishes) {
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
        Object dish = dishes.get(position);

        ImageView dish_img = (ImageView) holder.dish_img;

        TextView dish_name = (TextView) holder.name;

        Button price_btn = (Button) holder.price_btn;

        CardView dish_card = (CardView) holder.dish_card;
        dish_card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(holder.dish_card.getContext());
                dialog.setContentView(R.layout.item_dish_full);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }
}