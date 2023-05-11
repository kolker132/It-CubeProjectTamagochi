package com.example.kirill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kirill.retro.RetroHelper;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkActivity extends AppCompatActivity {
    Button button, save;
    TextView tv;
    EditText email,pwd;
    ListView usersLV;
    ArrayList<User> users = new ArrayList<>();
    Retrofit r;
    ArrayAdapter a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        tv = new TextView(getBaseContext());
        button = new Button(getBaseContext());
        save = new Button(getBaseContext());
        email = new EditText(getBaseContext());
        pwd = new EditText(getBaseContext());
        usersLV = new ListView(getBaseContext());
        tv.setHint("Тут ответ с сервера");
        button.setText("Отправить запрос");
        save.setText("Создать пользователся");
        save.setOnClickListener((v) -> save());

        LinearLayout layout = findViewById(R.id.layout5);

        layout.addView(tv);
        layout.addView(button);
        layout.addView(save);
        layout.addView(email);
        layout.addView(pwd);
        layout.addView(usersLV);
        a = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, users);
        usersLV.setAdapter(a);

        r = RetroHelper.getServer();

    }

    public void save() {

        UserService s = r.create(UserService.class);
        User user = new User("Lastname" + Math.random(),"Name" + Math.random(), 6);
        Call<Void> call = s.saveUser(user);
        call.enqueue(new MyCallback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                send();

            }
        });
    }
    public void send() {
        UserService s = r.create(UserService.class);
        Call<List<User>> call = s.users(email.getText().toString(), pwd.getText().toString());
        call.enqueue(new MyCallback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> body = response.body();
                users.clear();
                users.addAll(body);
                a.notifyDataSetChanged();
            }
        });
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            try {
                URL  url = new URL("http://192.168.1.119:8080");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                Scanner scanner = new Scanner(connection.getInputStream());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(scanner.nextLine());
                    }
                });

            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
    }
}