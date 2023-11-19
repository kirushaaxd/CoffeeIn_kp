package com.example.coffeein_kp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentAuthorization extends Fragment {

    EditText mail;
    EditText password;
    Button btn_auth;
    Button btn_reg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_auth, container, false);

        mail = (EditText) v.findViewById(R.id.mail);
        password = (EditText) v.findViewById(R.id.password);
        btn_auth = (Button) v.findViewById(R.id.btn_auth);
        btn_reg = (Button) v.findViewById(R.id.btn_reg);

        btn_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivityApp.class);
                startActivity(intent);
            }
        }
        );

        btn_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //MainActivity.fragmentRegistration = new FragmentRegistration();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(MainActivity.host.getId(), MainActivity.fragmentRegistration);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return v;
    }


}