package com.pratamatechnocraft.aplikasidatabasesqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pratamatechnocraft.aplikasidatabasesqlite.adapter.Adapter;
import com.pratamatechnocraft.aplikasidatabasesqlite.helper.DBHelper;
import com.pratamatechnocraft.aplikasidatabasesqlite.model.Data;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder builder;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    DBHelper dbHelper = new DBHelper(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DBHelper(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        listView=findViewById(R.id.list_view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, AddEditActivity.class);
                startActivity(intent);
            }
        });

        adapter = new Adapter(MainActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final CharSequence[] dialogItem = {"Edit","Delete"};
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                                intent.putExtra("id", itemList.get(position).getId());
                                intent.putExtra("name", itemList.get(position).getName());
                                intent.putExtra("address", itemList.get(position).getAddress());
                                startActivity(intent);
                                break;
                            case 1:
                                dbHelper.delete(Integer.parseInt(itemList.get(position).getId()));
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row =dbHelper.getAllData();
        for (int i=0;i<row.size();i++){
            Data data = new Data(
                    row.get(i).get("id"),
                    row.get(i).get("name"),
                    row.get(i).get("address")
            );
            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }
}