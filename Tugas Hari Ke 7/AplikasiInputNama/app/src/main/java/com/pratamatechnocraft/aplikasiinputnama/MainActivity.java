package com.pratamatechnocraft.aplikasiinputnama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextNamaLengkap, editTextAlamat;
    Button buttonSimpan, buttonBersihkan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Edit Text
        editTextNamaLengkap = findViewById(R.id.editTextNamaLengkap);
        editTextAlamat = findViewById(R.id.editTextAlamat);

        //Button
        buttonSimpan = findViewById(R.id.buttonSimpan);
        buttonBersihkan = findViewById(R.id.buttonBersihkan);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama, alamat;
                if(editTextNamaLengkap.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Nama lengkap wajib diisi", Toast.LENGTH_SHORT).show();
                    editTextNamaLengkap.requestFocus();
                }else if(editTextAlamat.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Alamat wajib diisi", Toast.LENGTH_SHORT).show();
                    editTextAlamat.requestFocus();
                }else{
                    nama = editTextNamaLengkap.getText().toString();
                    alamat = editTextAlamat.getText().toString();
                    Toast.makeText(MainActivity.this, "Selamat datang "+nama+" yang beralamat di "+alamat, Toast.LENGTH_SHORT).show();
                    editTextAlamat.setText("");
                    editTextNamaLengkap.setText("");
                }
            }
        });

    }

    public void reset(View view) {
        editTextAlamat.setText("");
        editTextNamaLengkap.setText("");
    }
}