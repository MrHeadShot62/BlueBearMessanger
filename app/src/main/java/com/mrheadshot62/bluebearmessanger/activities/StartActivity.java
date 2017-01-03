package com.mrheadshot62.bluebearmessanger.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mrheadshot62.bluebearmessanger.Activities;
import com.mrheadshot62.bluebearmessanger.R;
import com.mrheadshot62.bluebearmessanger.interfaces.InterfaceStartActivity;

public class StartActivity extends AppCompatActivity implements InterfaceStartActivity{
    public static String name;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.start_scanner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToScannerActivity();
            }
        });
        findViewById(R.id.start_server).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToServerActivity();
            }
        });
        editText = (EditText)findViewById(R.id.editText_name);
    }

    @Override
    public void goToServerActivity() {
        if (editText.getText().length() >0){
            name = editText.getText().toString();
        }else{
            Toast.makeText(this, "Введите имя пользователя", Toast.LENGTH_LONG).show();
            return;
        }
        startActivityForResult(new Intent(this, ServerActivity.class), Activities.SERVERACTIVITY);
    }

    @Override
    public void goToScannerActivity() {
        if (editText.getText().length() >0){
            name = editText.getText().toString();
        }else{
            Toast.makeText(this, "Введите имя пользователя", Toast.LENGTH_LONG).show();
            return;
        }
        startActivityForResult(new Intent(this, ScannerActivity.class), Activities.SCANNERACTIVITY);
    }
}