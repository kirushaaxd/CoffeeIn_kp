package com.example.coffeein_kp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedHashMap;
import java.util.List;

public class AdapterOrderStory extends RecyclerView.Adapter<AdapterOrderStory.ViewHolder> {
public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView orderTime;
    public TextView finalPrice;
    Button reorder;
    RecyclerView recycler;



    public ViewHolder(View itemView) {
        super(itemView);

        orderTime = (TextView) itemView.findViewById(R.id.orderTime);
        finalPrice = (TextView) itemView.findViewById(R.id.finalPrice);
        reorder = (Button) itemView.findViewById(R.id.reorder);
        recycler = (RecyclerView) itemView.findViewById(R.id.recycler);

    }
}

    private List<Order> order_story;

    public AdapterOrderStory(List<Order> order_story) {
        this.order_story = order_story;
    }

    @Override
    public AdapterOrderStory.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_order_story, parent, false);

        AdapterOrderStory.ViewHolder viewHolder = new AdapterOrderStory.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterOrderStory.ViewHolder holder, int position) {
        Order order = order_story.get(position);

        TextView orderTime = (TextView) holder.orderTime;
        orderTime.setText(order.getOrderDate() + ", " + order.getOrderTime());

        TextView finalPrice = (TextView) holder.finalPrice;
        finalPrice.setText(order.getFinalQuantity() + " руб.");

        Button reorder = (Button) holder.reorder;
        reorder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.reorder.getContext());
                 builder
                        .setTitle("Подтверждение заказа")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Вы хотите повторить заказ?")
                        .setPositiveButton("Повторить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == DialogInterface.BUTTON_POSITIVE){
                                    StaticResources.ordersList.clear();
                                    StaticResources.ordersList = new LinkedHashMap<>();
                                    for (int i = 0; i < order.getProducts().size(); i++) {
                                        OrderProduct orderProduct = order.getProducts().get(i);
                                        StaticResources.ordersList.put(orderProduct.getProduct(), orderProduct.getCount());
                                    }
                                    //MainActivityApp.fragmentOrder.updateOrderInfo();
                                    Toast.makeText(reorder.getContext(), "Товары добавлены в корзину", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Отменить", null)
                        .create().show();
            }
        });

        RecyclerView recycler = (RecyclerView) holder.recycler;

        AdapterOrdersStoryItem adapter = new AdapterOrdersStoryItem(order.getProducts());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext()));

    }

    @Override
    public int getItemCount() {
        return order_story.size();
    }
}
