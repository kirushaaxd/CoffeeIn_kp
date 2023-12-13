package com.example.coffeein_kp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.HashMap;
import java.util.Map;

public class FragmentProfile extends Fragment {
    EditText name;
    Button changeName;
    Button myOrders;
    Button help;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        name = (EditText) v.findViewById(R.id.name);
        changeName = (Button) v.findViewById(R.id.changeName);
        myOrders = (Button) v.findViewById(R.id.myOrders);
        help = (Button) v.findViewById(R.id.help);

        name.setText(StaticResources.currentClient.getName());
        changeName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (name.getText().toString().length() > 2){
                    changeName();
                }
                else{
                    Toast.makeText(getContext(), "Слишком короткое имя", Toast.LENGTH_SHORT);
                }
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder
                        .setTitle("Техподдержка")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setMessage("Вы хотите обратиться в техподдержку за  помощью?")
                        .setPositiveButton("Позвонить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == DialogInterface.BUTTON_POSITIVE){
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+78002220000"));
                                    help.getContext().startActivity(intent);
                                }
                            }
                        })
                        .setNegativeButton("Отменить", null)
                        .create().show();
            }
        });

        myOrders.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (StaticResources.orderStory == null)
                    StaticResources.orderStory = new FragmentOrderStory();
                StaticResources.orderStory.show(StaticResources.fragmentManager, "orderStory");
            }
        });


        return v;
    }

    public void changeName(){
        // Смена имени пользователя в Firebase
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("Имя", name.getText().toString());

        StaticResources.currentClientDocumentReference.update(updateData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Имя успешно изменено", Toast.LENGTH_SHORT).show();
                        StaticResources.currentClient.setName(name.getText().toString());
                        Log.d("", "Поле 'Имя' успешно обновлено");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("", "Не удалось обновить поле 'Имя'", e);
                    }
                });
    }
}
