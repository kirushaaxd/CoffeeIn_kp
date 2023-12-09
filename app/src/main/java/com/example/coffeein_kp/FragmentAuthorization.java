package com.example.coffeein_kp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
            // Обработка нажатия на кнопку авторизации
            @Override
            public void onClick(View v) {
                if (checkFields())
                    signIn();
                else
                    Toast.makeText(getContext(), "Заполнены не все поля", Toast.LENGTH_LONG);
            }
        }
        );

        btn_reg.setOnClickListener(new View.OnClickListener(){
            // Обработка нажатия на кнопку перехода к регистрации
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

    public void signIn(){
        // Авторизация пользователя в Firebase
        StaticResources.mAuth.signInWithEmailAndPassword(mail.getText().toString(), password.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            authUser();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Убедитесь, что данные введены корректно", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void authUser(){
        // Получение данных об авторизованном пользователе
        StaticResources.fBase.collection("Клиенты")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            boolean success = false;

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getString("Почта").equals(mail.getText().toString())){
                                    StaticResources.currentClient = new Client(document.getString("Имя"), document.getString("Пароль"),
                                            document.getString("Почта"));

                                    StaticResources.currentClient.setDocId(document.getId());
                                    success = true;
                                    break;
                                }
                            }

                            if (success){
                                Toast.makeText(getContext(), "Успешная авторизация", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getContext(), MainActivityApp.class);
                                startActivity(intent);
                            }
                        }
                        else {
                            Toast.makeText(getContext(), "Пользоватеь не найден", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public boolean checkFields(){
        // Проверка на то, что все текстовые поля заполнены
        return (!mail.getText().toString().isEmpty() && !password.getText().toString().isEmpty());
    }


}