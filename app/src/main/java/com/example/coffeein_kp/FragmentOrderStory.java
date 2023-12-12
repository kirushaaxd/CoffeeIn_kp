package com.example.coffeein_kp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FragmentOrderStory extends BottomSheetDialogFragment {
    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_order_story_full, container, false);

        recycler = (RecyclerView) v.findViewById(R.id.recycler);

        AdapterOrderStory adapter = new AdapterOrderStory(StaticResources.ordersStory);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }
}