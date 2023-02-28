package com.example.kirill;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

public class ListActivity2 extends AppCompatActivity {
    ListView mylist;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        mylist = findViewById(R.id.mylist);
        ArrayAdapter<MyMonth> adapter = new MyMonthAdapter(
                getBaseContext(),
                makeList()
        );
        mylist.setAdapter(adapter);
        mylist.setOnItemClickListener((aV, v, pos, id) -> {
            Toast.makeText(getBaseContext(),adapter.getItem(pos).toString(), Toast.LENGTH_SHORT).show();
        });
    }
    public ArrayList<MyMonth> makeList() {
        ArrayList<MyMonth> months = new ArrayList<>();
        months.add(new MyMonth("Jan", -15, 31));
        months.add(new MyMonth("Feb", -10, 28));
        months.add(new MyMonth("Mar", -5, 31));
        months.add(new MyMonth("Apr", 0, 30));
        months.add(new MyMonth("May", 15, 31));
        months.add(new MyMonth("June", 25, 30));
        months.add(new MyMonth("July", 30, 31));
        months.add(new MyMonth("Avg", 15, 30));
        months.add(new MyMonth("Sep", 10, 31));
        months.add(new MyMonth("Oct", 5, 30));
        months.add(new MyMonth("Nov", -5, 31));
        months.add(new MyMonth("Dec", -15, 30));
        return months;
    }
}