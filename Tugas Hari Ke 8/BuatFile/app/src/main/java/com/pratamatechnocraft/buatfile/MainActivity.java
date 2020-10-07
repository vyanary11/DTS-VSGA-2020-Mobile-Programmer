package com.pratamatechnocraft.buatfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "namafile.txt";
    Button buttonBuatFile, buttonUbahFile, buttonBacaFile, buttonHapusFile;
    TextView textViewBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonBuatFile = findViewById(R.id.buttonBuatFile);
        buttonUbahFile = findViewById(R.id.buttonUbahFile);
        buttonBacaFile = findViewById(R.id.buttonBacaFile);
        buttonHapusFile = findViewById(R.id.buttonHapusFile);

        textViewBaca = findViewById(R.id.textViewBaca);

        buttonBuatFile.setOnClickListener(this);
        buttonUbahFile.setOnClickListener(this);
        buttonBacaFile.setOnClickListener(this);
        buttonHapusFile.setOnClickListener(this);
    }

    void buatFile(String isiFile){
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream fileOutputStream = null;
        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file, false);
            fileOutputStream.write(isiFile.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);

       if (file.exists()){
           file.delete();
       }
    }

    void bacaFile(){
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()){
            StringBuilder stringBuilder = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                String line = bufferedReader.readLine();

                while (line!=null){
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }

            textViewBaca.setText(stringBuilder.toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBuatFile:
                buatFile("Ini Filenya");
                break;
            case R.id.buttonUbahFile:
                buatFile("Ini Filenya Diubah");
                break;
            case R.id.buttonBacaFile:
                bacaFile();
                break;
            case R.id.buttonHapusFile:
                hapusFile();
                break;
        }
    }
}