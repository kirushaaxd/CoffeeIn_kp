package com.example.coffeein_kp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentRegistration extends Fragment {

    EditText name;
    EditText mail;
    EditText password1;
    EditText password2;
    Button btn_reg;
    Button btn_auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reg, container, false);

        name = (EditText) v.findViewById(R.id.name);
        mail = (EditText) v.findViewById(R.id.mail);
        password1 = (EditText) v.findViewById(R.id.password1);
        password2 = (EditText) v.findViewById(R.id.password2);
        btn_reg = (Button) v.findViewById(R.id.btn_reg);
        btn_auth = (Button) v.findViewById(R.id.btn_auth);

        btn_auth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.fragmentAuthorization = new FragmentAuthorization();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(MainActivity.host.getId(), MainActivity.fragmentAuthorization);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return v;
    }


}
