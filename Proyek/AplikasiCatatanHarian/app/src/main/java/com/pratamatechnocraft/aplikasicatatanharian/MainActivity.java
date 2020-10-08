package com.pratamatechnocraft.aplikasicatatanharian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listViewCatatan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         listViewCatatan = findViewById(R.id.listViewCatatan);
         listViewCatatan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Intent intent = new Intent(MainActivity.this, InsertAndViewActivity.class);
                 Map<String, Object> data = (Map<String, Object>) adapterView.getAdapter().getItem(i);
                 intent.putExtra("filename", data.get("name").toString());
                 Toast.makeText(MainActivity.this, "You Clicked "+data.get("name").toString(), Toast.LENGTH_SHORT);
                 startActivity(intent);
             }
         });

         listViewCatatan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
             @Override
             public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Map<String, Object> data = (Map<String, Object>) adapterView.getAdapter().getItem(i);
                 tampilkanDialogKonfirmasiHapus(data.get("name").toString());
                 return true;
             }
         });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23){
            if (periksaIziPeyimpanan()){
                mengambilListPadaFolder();
            }
        }else{
            mengambilListPadaFolder();
        }
    }

    private void mengambilListPadaFolder() {
        String path = Environment.getExternalStorageDirectory().toString() + "/kominfo.proyek1";
        File directory =new File(path);

        if (directory.exists()){
            File[] files = directory.listFiles();
            String[] filenames = new String[files.length];
            String[] dateCreated = new String[files.length];
            SimpleDateFormat simpleDateFormat = new  SimpleDateFormat("dd MMM YYYY HH:mm:ss");
            ArrayList<Map<String, Object>> itemDataList = new ArrayList<Map<String, Object>>();

            for (int i=0;i<files.length;i++){
                filenames[i] = files[i].getName();
                Date lastModDate = new Date(files[i].lastModified());
                dateCreated[i] = simpleDateFormat.format(lastModDate);
                Map<String, Object> listItemMap = new HashMap<>();
                listItemMap.put("name", filenames[i]);
                listItemMap.put("date", dateCreated[i]);
                itemDataList.add(listItemMap);
            }

            SimpleAdapter simpleAdapter = new SimpleAdapter(this, itemDataList, android.R.layout.simple_list_item_2, new String[]{"name","date"}, new int[]{android.R.id.text1,android.R.id.text2});
            listViewCatatan.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
        }
    }

    private boolean periksaIziPeyimpanan() {
        if (Build.VERSION.SDK_INT >= 23){
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                return true;
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                return false;
            }
        }else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 100:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    mengambilListPadaFolder();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.tambahCatatan){
            Intent intent = new Intent(MainActivity.this, InsertAndViewActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void tampilkanDialogKonfirmasiHapus(final String nama) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Catatan ini ?")
                .setMessage("Anda Yakin menghapus Catatan "+nama+" ?")
                .setIcon(R.drawable.ic_baseline_add_24_white)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hapusFile(nama);
                    }
                })
                .setNegativeButton("Tidak", null).show();
    }

    private void hapusFile(String nama) {
        String path = Environment.getExternalStorageDirectory().toString() + "/kominfo.proyek1";
        File file = new File(path, nama);
        if (file.exists()){
            file.delete();
        }
        mengambilListPadaFolder();
    }
}