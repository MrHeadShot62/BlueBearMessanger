package com.mrheadshot62.bluebearmessanger.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mrheadshot62.bluebearmessanger.R;
import com.mrheadshot62.bluebearmessanger.datas.Configuration;
import com.mrheadshot62.bluebearmessanger.interfaces.InterfaceServerActivity;
import com.mrheadshot62.bluebearmessanger.server.ServerController;

public class ServerActivity extends AppCompatActivity implements InterfaceServerActivity{
    private EditText name, password, desc;
    private CheckBox friendsOnly;
    private Button buttonStart;
    private Configuration configuration;
    public static ServerController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        name = (EditText)findViewById(R.id.editText_name);
        desc = (EditText)findViewById(R.id.editText_desc);
        password = (EditText)findViewById(R.id.editText_pass);
        friendsOnly = (CheckBox)findViewById(R.id.checkBox_friends);
        configuration = new Configuration();
        buttonStart = (Button) findViewById(R.id.create_server);
        final Context context = this;
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startServer();
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("isServer", true);
                startActivity(intent);
            }
        });
    }


    @Override
    public void friendsOnly() {
        configuration.setFriendsOnly(friendsOnly.isChecked());
    }

    @Override
    public void startServer() {
        try {
            friendsOnly();
            setServerName(name.getText().toString());
            setDescription(desc.getText().toString());
            setPassword(password.getText().toString());

            controller = new ServerController(); //TODO go to chat activity
            controller.startServer(configuration);
        }catch(Exception e){
            Log.e("ServerActivity", e.getMessage(), e);
        }

    }

    @Override
    public void setServerName(String serverName) {
        configuration.setName(serverName);
    }

    @Override
    public void setBlacklist(String[] names) {

    }

    @Override
    public void setPassword(String password) {
        configuration.setPassword(password);
    }

    @Override
    public void setDescription(String description) {
        configuration.setDescription(description);
    }
}
