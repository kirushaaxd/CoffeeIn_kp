package com.example.coffeein_kp;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        makeOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder
                        .setTitle("Подтверждение заказа")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setMessage("Вы уверены, что хотите оформить заказ?")
                        .setPositiveButton("Заказать", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == DialogInterface.BUTTON_POSITIVE)
                                    FragmentOrder.createOrder();
                            }
                        })
                        .setNegativeButton("Отменить", null)
                        .create().show();
            }
        });

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
            int Price = 0;
            for (int i = 0; i < StaticResources.ordersList.size(); i++){
                Product orderProduct = (Product) StaticResources.ordersList.keySet().toArray()[i];
                Price += orderProduct.getQuantity() * StaticResources.ordersList.get(orderProduct);
            }
            finalPrice.setText(Price + "");
        }

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext()));
    }

    public static void createOrder(){
        // Добавление заказа в Firebase
        if (StaticResources.selectedCoffeeHouse == null){
            Toast.makeText(recycler.getContext(), "Выберите адрес для заказа", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> order = new HashMap<>();

        CollectionReference collectionRef = StaticResources.fBase.collection("Кофейни");
        DocumentReference documentRef = collectionRef.document(StaticResources.selectedCoffeeHouse.getShopId());
        order.put("Адрес кофейни", documentRef);

        if (LocalDateTime.now().getMinute() > 9)
            order.put("Время заказа", LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute());
        else
            order.put("Время заказа", LocalDateTime.now().getHour() + ":0" + LocalDateTime.now().getMinute());
        if (LocalDateTime.now().getMonthValue() > 9)
            order.put("Дата заказа", LocalDateTime.now().getDayOfMonth() + "/" + LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getYear());
        else
            order.put("Дата заказа", LocalDateTime.now().getDayOfMonth() + "/0" + LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getYear());
        order.put("Итоговая стоимость", Integer.parseInt(finalPrice.getText().toString()));

        List<Map<String, Object>> orderProducts = new ArrayList<>();
        for (int i = 0; i < StaticResources.ordersList.size(); i++){
            Map<String, Object> map = new HashMap<>();
            Product product = (Product) StaticResources.ordersList.keySet().toArray()[i];
            map.put("Кол-во товара", StaticResources.ordersList.get(product));

            collectionRef = StaticResources.fBase.collection("Товары");
            documentRef = collectionRef.document(product.getDocumentID());
            map.put("Название товара", documentRef);

            map.put("Стоимость позиций", product.getQuantity() * StaticResources.ordersList.get(product));

            orderProducts.add(map);
        }
        order.put("Товары в заказе", orderProducts);

        StaticResources.fBase.collection("Заказы")
                .add(order)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(recycler.getContext(), "Заказ успешно создан", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                        addOrderToClient(documentReference);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(recycler.getContext(), "Не удалось оформить заказ", Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        StaticResources.ordersList  = new LinkedHashMap<>();
        updateOrderInfo();
    }

    public static void addOrderToClient(DocumentReference documentReference) {
        // Добавление заказа в историю заказов текущего пользователя
        StaticResources.clientOrdersDocumentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            List<DocumentReference> ordersArray = (List<DocumentReference>) documentSnapshot.get("Заказы");
                            ordersArray.add(documentReference);

                            StaticResources.clientOrdersDocumentReference.update("Заказы", ordersArray)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "Массив 'Заказы' успешно обновлен");
                                            MainActivityApp.LoadClientsOrders();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Не удалось обновить массив 'Заказы'", e);
                                        }
                                    });
                        } else {
                            Log.d(TAG, "Документ 'Заказы клиентов' не существует");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Ошибка при получении документа
                        Log.w(TAG, "Не удалось получить документ 'Заказы клиентов'", e);
                    }
                });
    }
}
