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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        btn_reg.setOnClickListener(new View.OnClickListener(){
            // Нажатие на кнопку регистрации - обработка проверок + выполнение регистрации
            @Override
            public void onClick(View v) {
                //MainActivity.fragmentAuthorization = new FragmentAuthorization();

                if (checkFields())
                {
                    if (password1.getText().toString().equals(password2.getText().toString()))
                        signUp(mail.getText().toString(), password1.getText().toString());
                    else
                        Toast.makeText(getContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });

        btn_auth.setOnClickListener(new View.OnClickListener(){
            // Нажатие на кнопку возврата к авторизации - перемещение на другой экран
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(MainActivity.host.getId(), MainActivity.fragmentAuthorization);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return v;
    }

    public boolean checkFields(){
        // Проверка на то, что все текстовые поля заполнены
        return (!name.getText().toString().isEmpty() && !mail.getText().toString().isEmpty() &&
                !password1.getText().toString().isEmpty() && !password2.getText().toString().isEmpty());
    }

    public void signUp(String email, String password) {
        // Регистрация пользователя в Firebase
        StaticResources.mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            regUser();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Указанная почта уже зарегистрирована", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void regUser(){
        // Добавление пользователя в соответствующую таблицу Firebase
        StaticResources.currentClient = new Client(name.getText().toString(), password1.getText().toString(), mail.getText().toString());

        Map<String, Object> user = new HashMap<>();
        user.put("Имя", StaticResources.currentClient.getName());
        user.put("Пароль", StaticResources.currentClient.getPassword());
        user.put("Почта", StaticResources.currentClient.getMail());

        StaticResources.fBase.collection("Клиенты")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "Успешная регистрация", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                        StaticResources.currentClient.setDocId(documentReference.getId());
                        StaticResources.currentClientDocumentReference = documentReference;

                        createUserOrdersStory(documentReference);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Ошибка регистрации. Почта уже зарегистрирована", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void createUserOrdersStory(DocumentReference documentReference1){
        // Создание коллекции со всеми заказами нового клиента
        Map<String, Object> userOrderStory = new HashMap<>();
        userOrderStory.put("Клиент", documentReference1);
        userOrderStory.put("Заказы", new ArrayList<>());

        StaticResources.fBase.collection("Заказы клиентов")
                .add(userOrderStory)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                        StaticResources.clientOrdersDocumentReference = documentReference;

                        Intent intent = new Intent(getContext(), MainActivityApp.class);
                        startActivity(intent);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Ошибка регистрации. Почта уже зарегистрирована", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
