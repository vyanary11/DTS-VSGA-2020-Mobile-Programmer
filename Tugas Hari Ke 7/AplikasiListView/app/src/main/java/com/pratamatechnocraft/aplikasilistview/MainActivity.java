package com.pratamatechnocraft.aplikasilistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ListView listViewNegara;

    private String[] negara = {"Indonesia","Malaysia","Singapore","Italia","Inggris",
            "Belanda","Argentina","Chile","Mesir","Uganda","Vietnam","Australia","Brazil","Amerika Serikat"};

    private ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNegara = findViewById(R.id.listVIewNegara);
        data = new ArrayList<>();
        Collections.addAll(data, negara);
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, data);
        listViewNegara.setAdapter(adapter);
        listViewNegara.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Negara yang anda pilih "+data.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }
}