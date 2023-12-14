package com.example.coffeein_kp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

public class FragmentDishInfo extends BottomSheetDialogFragment {
    ImageView dish_img;
    TextView name;
    TextView description;
    TextView compose;
    TextView calories;

    Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_dish_full, container, false);

        dish_img = (ImageView) v.findViewById(R.id.dish_img);
        name = (TextView) v.findViewById(R.id.name);
        description = (TextView) v.findViewById(R.id.description);
        compose = (TextView) v.findViewById(R.id.compose);
        calories = (TextView) v.findViewById(R.id.calories);

        Picasso.with(getContext()).load(product.getImage())
                .error(R.drawable.app_small)
                .placeholder(R.drawable.app_small)
                .resize(250, 250)
                .into(dish_img);

        name.setText(product.getName());
        description.setText(product.getDescription());
        compose.setText(product.getCompound());
        calories.setText(product.getCalories() + " кал.");

        return v;
    }

    public void TransferData(Product product){
        // Передача информации для отображения по конкретному блюду
        this.product = product;
    }

}