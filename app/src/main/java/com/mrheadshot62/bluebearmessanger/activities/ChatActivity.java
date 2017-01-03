package com.mrheadshot62.bluebearmessanger.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrheadshot62.bluebearmessanger.R;
import com.mrheadshot62.bluebearmessanger.client.ClientController;
import com.mrheadshot62.bluebearmessanger.datas.Message;
import com.mrheadshot62.bluebearmessanger.datas.User;
import com.mrheadshot62.bluebearmessanger.server.ServerController;

public class ChatActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private static LinearLayout container;
    private static Context context;
    private ClientController clientController;
    private ServerController serverController;
    private boolean isServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        context = this;
        container = (LinearLayout)findViewById(R.id.container);
        button = (Button)findViewById(R.id.button_send);
        editText = (EditText)findViewById(R.id.editText_mess);
        setListeners();
    }
    public static void printMessage(Message mess){
        TextView textView = new TextView(context);
        textView.setText(mess.getAuthor().getName()+": "+mess.getMessage());
        container.addView(textView);
    }

    private void setListeners(){
        if (getIntent().getBooleanExtra("isServer", false)){
            isServer = true;
            serverController = ServerActivity.controller;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Sending", "s");
                    if (editText.getText().length()>0){
                        Message mess = new Message(editText.getText().toString(), new User(StartActivity.name));
                        serverController.sendMessage(mess);
                        editText.setText("");
                        printMessage(mess);
                    }
                }
            });
        }else{
            isServer = false;
            clientController = ScannerActivity.controller;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Sending", "s");
                    if (editText.getText().length()>0){
                        Message mess = new Message(editText.getText().toString(), new User(StartActivity.name));
                        clientController.sendMessage(mess);
                        editText.setText("");
                        printMessage(mess);
                    }
                }
            });
        }
    }
}
