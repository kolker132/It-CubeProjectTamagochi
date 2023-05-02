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

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkActivity extends AppCompatActivity {
    Button button;
    TextView tv;
    EditText email,pwd;
    ListView usersLV;
    ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        tv = new TextView(getBaseContext());
        button = new Button(getBaseContext());
        email = new EditText(getBaseContext());
        pwd = new EditText(getBaseContext());
        usersLV = new ListView(getBaseContext());
        tv.setHint("Тут ответ с сервера");
        button.setText("Отправить запрос");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        LinearLayout layout = findViewById(R.id.layout5);

        layout.addView(tv);
        layout.addView(button);
        layout.addView(email);
        layout.addView(pwd);
        layout.addView(usersLV);
        ArrayAdapter a = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, users);
        usersLV.setAdapter(a);
    }
    public void send() {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
        .add("email", email.getText().toString())
        .add("pwd", pwd.getText().toString())
        .build();
        Request request = new Request.Builder()
                .url("http://192.168.1.119:8080/user")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int code = response.code();
                String body = response.body().string();
                if (code < 300) {
                    runOnUiThread(() -> {
                        Gson gs = new Gson();
                        User[] u = gs.fromJson(body, User[].class);
                        tv.setText(body);
                        users.clear();
                        users.addAll(Arrays.asList(u));
                    });
                }else {
                    runOnUiThread(() -> {
                        tv.setText("Произошла ошибка!" + code);
                    });
                }
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