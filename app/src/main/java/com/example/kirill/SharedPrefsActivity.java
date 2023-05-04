package com.example.kirill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SharedPrefsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText key, val;
    Button LOAD, SAVE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);
        key = findViewById(R.id.key);
        val = findViewById(R.id.val);
        LOAD = findViewById(R.id.LOAD);
        SAVE = findViewById(R.id.SAVE);
        sharedPreferences = getSharedPreferences("allpres", MODE_PRIVATE);
        LOAD.setOnClickListener((v) -> LOAD());
        SAVE.setOnClickListener((v) -> SAVE());
    }
    public void LOAD() {
        String s = sharedPreferences
                .getString(key.getText().toString(),"Пусто");
        val.setText(s);
    }
    public void SAVE() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(
                key.getText().toString(), val.getText().toString());
        editor.apply();
        key.setText("");
        val.setText("");
    }
}