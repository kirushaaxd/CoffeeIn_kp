package com.example.coffeein_kp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(getContext());
                dialog.setContentView(R.layout.item_help);
                TextView call = (TextView) dialog.findViewById(R.id.call);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + call.getText().toString()));
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });

        myOrders.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(getContext());
                dialog.setContentView(R.layout.item_order_story_full);

                RecyclerView recycler = dialog.findViewById(R.id.recycler);

                AdapterOrderStory adapter = new AdapterOrderStory(StaticResources.orders);
                recycler.setAdapter(adapter);
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                dialog.show();

            }
        });


        return v;
    }
}
