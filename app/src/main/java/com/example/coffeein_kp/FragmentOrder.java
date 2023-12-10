package com.example.coffeein_kp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentOrder extends Fragment {
    static RecyclerView recycler;
    static TextView message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order, container, false);

        recycler = (RecyclerView) v.findViewById(R.id.recycler);
        message = (TextView) v.findViewById(R.id.message);

        updateRecycler();
        return v;
    }

    public static void updateRecycler(){
        // Обновление списка товаров при любом изменении/добавлении/удалении товаров в корзине
        AdapterOrders adapter = new AdapterOrders(StaticResources.ordersList);
        if (StaticResources.ordersList.size() == 0)
            message.setVisibility(View.VISIBLE);
        else
            message.setVisibility(View.INVISIBLE);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext()));
    }
}
