package com.mrheadshot62.bluebearmessanger.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mrheadshot62.bluebearmessanger.R;
import com.mrheadshot62.bluebearmessanger.client.ClientController;
import com.mrheadshot62.bluebearmessanger.interfaces.InterfaceScannerActivity;

public class ScannerActivity extends AppCompatActivity implements InterfaceScannerActivity{
    private Button buttonConnect;
    private EditText ip;
    public static ClientController controller;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        buttonConnect = (Button)findViewById(R.id.button_connect);
        ip = (EditText)findViewById(R.id.editText_ip);
        ScannerActivity.context = this;
        final Context context = this;
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller = new ClientController();
                controller.startThread(ip.getText().toString());
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("isServer", false);
                startActivity(intent);
            }
        });
    }

    public static String getPass(){
        final String[] pass=new String[1];
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.pass_check, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
        mDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                pass[0]=userInput.getText().toString();
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
        return pass[0];
    }

    @Override
    public void refreshNetwork() {

    }

    @Override
    public void scannNetwork() {

    }

    @Override
    public void getServerInfo() {

    }

    @Override
    public void connectToServer() {

    }
}
