package com.pratamatechnocraft.tugasharike4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn10;
    Button btn11;
    Button btn12;
    Button btn13;
    Button btn14;
    Button btn15;
    Button btn16;

    Boolean firstClick = true;
    int firstId = 0;
    String tmpFirstClick = null;

    String[] alfabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O",""};
    List<String> alfabetList = Arrays.asList(alfabet);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 =  findViewById(R.id.btn1);
        btn2 =  findViewById(R.id.btn2);
        btn3 =  findViewById(R.id.btn3);
        btn4 =  findViewById(R.id.btn4);
        btn5 =  findViewById(R.id.btn5);
        btn6 =  findViewById(R.id.btn6);
        btn7 =  findViewById(R.id.btn7);
        btn8 =  findViewById(R.id.btn8);
        btn9 =  findViewById(R.id.btn9);
        btn10 =  findViewById(R.id.btn10);
        btn11 =  findViewById(R.id.btn11);
        btn12 =  findViewById(R.id.btn12);
        btn13 =  findViewById(R.id.btn13);
        btn14 =  findViewById(R.id.btn14);
        btn15 =  findViewById(R.id.btn15);
        btn16 =  findViewById(R.id.btn16);

        ulangi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuMainUlang){
            ulangi();
        }else if (item.getItemId() == R.id.menuKeluar){
            finish();
        }
        return true;
    }

    private void ulangi(){
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        btn10.setText("");
        btn11.setText("");
        btn12.setText("");
        btn13.setText("");
        btn14.setText("");
        btn15.setText("");
        btn16.setText("");

        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
        btn10.setEnabled(true);
        btn11.setEnabled(true);
        btn12.setEnabled(true);
        btn13.setEnabled(true);
        btn14.setEnabled(true);
        btn15.setEnabled(true);
        btn16.setEnabled(true);

        Collections.shuffle(alfabetList);

        alfabetList.toArray(alfabet);

        btn1.setText(alfabet[0]);
        btn2.setText(alfabet[1]);
        btn3.setText(alfabet[2]);
        btn4.setText(alfabet[3]);
        btn5.setText(alfabet[4]);
        btn6.setText(alfabet[5]);
        btn7.setText(alfabet[6]);
        btn8.setText(alfabet[7]);
        btn9.setText(alfabet[8]);
        btn10.setText(alfabet[9]);
        btn11.setText(alfabet[10]);
        btn12.setText(alfabet[11]);
        btn13.setText(alfabet[12]);
        btn14.setText(alfabet[13]);
        btn15.setText(alfabet[14]);
        btn16.setText(alfabet[15]);
    }

    public void click_button(View view) {
        Button button = findViewById(view.getId());
        if (firstClick){
            firstId = view.getId();
            firstClick = false;
            tmpFirstClick = button.getText().toString();
            button.setEnabled(false);
        }else{
            int indexFirst = Arrays.asList(alfabet).indexOf(tmpFirstClick);
            int indexSekarang = Arrays.asList(alfabet).indexOf(button.getText().toString());
            int indexKanan = Arrays.asList(alfabet).indexOf(tmpFirstClick)+1;
            int indexBawah = Arrays.asList(alfabet).indexOf(tmpFirstClick)-1;
            int indexAtas = Arrays.asList(alfabet).indexOf(tmpFirstClick)-4;
            int indexKiri = Arrays.asList(alfabet).indexOf(tmpFirstClick)+4;
            Log.d("Index", "Sekarang: "+indexSekarang);
            Log.d("Index", "Atas: "+indexAtas);
            Log.d("Index", "Bawah: "+indexBawah);
            Log.d("Index", "Kiri: "+indexKiri);
            Log.d("Index", "Kanan: "+indexKanan);
            if (indexSekarang == indexKanan || indexSekarang == indexKiri || indexSekarang == indexAtas || indexSekarang == indexBawah){
                alfabet[indexSekarang] = tmpFirstClick;
                alfabet[indexFirst] = button.getText().toString();
                firstClick = true;
                Button buttonFirst = findViewById(firstId);
                buttonFirst.setEnabled(true);
                buttonFirst.setText(button.getText().toString());
                button.setText(tmpFirstClick);
                check_alfabet();
            }
        }
    }

    private void check_alfabet(){
        String[] alfabetUrut = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O",""};
        Boolean urut = false;
        for (int i=0;i<alfabetUrut.length;i++){
            if(alfabetUrut[i]!=alfabet[i]) {
                urut = false;
                break;
            }else{
                urut = true;
            }
        }
        if(urut==true){
            Toast.makeText(this,"You Win IT", Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Ingin Mengulangi ?");
                    builder.setPositiveButton("IYA", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ulangi();
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("KELUAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.setCanceledOnTouchOutside(false);
                    alert.show();
                }
            }, 700);
        }
    }
}