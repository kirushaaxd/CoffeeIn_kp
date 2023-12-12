package com.example.coffeein_kp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentOrder extends Fragment {
    static RecyclerView recycler;
    static TextView message;
    static LinearLayout mainOrder;
    static TextView finalPrice;
    static Button makeOrder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order, container, false);

        recycler = (RecyclerView) v.findViewById(R.id.recycler);
        message = (TextView) v.findViewById(R.id.message);
        mainOrder = (LinearLayout) v.findViewById(R.id.mainOrder);
        finalPrice = (TextView) v.findViewById(R.id.finalPrice);
        makeOrder = (Button) v.findViewById(R.id.makeOrder);

        updateOrderInfo();
        return v;
    }

    public static void updateOrderInfo(){
        // Обновление списка товаров при любом изменении/добавлении/удалении товаров в корзине
        AdapterOrders adapter = new AdapterOrders(StaticResources.ordersList);
        if (StaticResources.ordersList.size() == 0){
            message.setVisibility(View.VISIBLE);
            mainOrder.setVisibility(View.INVISIBLE);
        }
        else{
            message.setVisibility(View.INVISIBLE);
            mainOrder.setVisibility(View.VISIBLE);
        }

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext()));
    }

}
