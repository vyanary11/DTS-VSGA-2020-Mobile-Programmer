package com.pratamatechnocraft.aplikasikalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonBersihkan;
    EditText editTextAngkaPertama, editTextAngkaKedua;
    TextView textViewHasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Deklarasi Button
        buttonBersihkan = findViewById(R.id.buttonBersihkan);

        //Deklarasi Edit Text
        editTextAngkaPertama = findViewById(R.id.editTextAngkaPertama);
        editTextAngkaKedua = findViewById(R.id.editTextAngkaKedua);

        //Deklarasi Text View
        textViewHasil = findViewById(R.id.textViewHasil);

        //Fungsi button
        buttonBersihkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextAngkaPertama.setText("");
                editTextAngkaKedua.setText("");
                textViewHasil.setText("0");
            }
        });

    }

    public void operatorClick(View view) {
        if (editTextAngkaPertama.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "Angka pertama wajib diisi!!", Toast.LENGTH_SHORT).show();
            editTextAngkaPertama.requestFocus();
        }else if(editTextAngkaKedua.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "Angka kedua wajib diisi!!", Toast.LENGTH_SHORT).show();
            editTextAngkaKedua.requestFocus();
        }else{
            Double hasil = null;
            Double angka1, angka2;
            angka1 = Double.parseDouble(editTextAngkaPertama.getText().toString());
            angka2 = Double.parseDouble(editTextAngkaKedua.getText().toString());
            if (view.getId()==R.id.buttonPlus){
                hasil = angka1 + angka2;
            }else if (view.getId()==R.id.buttonMinus){
                hasil = angka1 - angka2;
            }else if(view.getId()==R.id.buttonKali){
                hasil = angka1 * angka2;
            }else if(view.getId()==R.id.buttonBagi){
                hasil = angka1 / angka2;
            }
            editTextAngkaPertama.setText("");
            editTextAngkaKedua.setText("");
            textViewHasil.setText(String.valueOf(hasil));
        }

    }
}