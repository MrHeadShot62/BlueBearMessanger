package com.mrheadshot62.bluebearmessanger.client;

import android.os.AsyncTask;
import android.util.Log;

import com.mrheadshot62.bluebearmessanger.activities.ChatActivity;
import com.mrheadshot62.bluebearmessanger.activities.ScannerActivity;
import com.mrheadshot62.bluebearmessanger.commands.Commands;
import com.mrheadshot62.bluebearmessanger.datas.Command;
import com.mrheadshot62.bluebearmessanger.datas.Datas;
import com.mrheadshot62.bluebearmessanger.datas.Message;
import com.mrheadshot62.bluebearmessanger.datas.Packet;
import com.mrheadshot62.bluebearmessanger.datas.Password;
import com.mrheadshot62.bluebearmessanger.sockets.WifiInputStream;
import com.mrheadshot62.bluebearmessanger.sockets.WifiOutputStream;

import java.io.IOException;

/**
 * Created by novak on 28.12.2016.
 */

class ClientListener extends AsyncTask {
    private final WifiInputStream in;
    private final WifiOutputStream out;

    public ClientListener(WifiInputStream in, WifiOutputStream out){
        this.in = in;
        this.out = out;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        ChatActivity.printMessage((Message)values[0]);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        boolean authorizated=false;
        do {
            try {
                out.writePacket(new Packet(new Password(ScannerActivity.getPass()), Datas.PASS));
                out.flush();
                Packet p = in.readPacket();
                if (p.getType() == Datas.COMMAND){
                    Command c = (Command)p.getData();
                    if (c.getCommand()==Commands.VALID){
                        if ((boolean)c.getArguments()[0]){
                            authorizated=true;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while (!authorizated);

        while (in!=null){
            try {
                Packet p = in.readPacket();
                switch (p.getType()){
                    case Datas.MESSAGE:
                        Message message = (Message)p.getData();
                        Log.d("Client Listener ", message.getMessage());
                        publishProgress(message);
                        break;
                    case Datas.COMMAND:
                        Command command = (Command)p.getData();
                        break;
                    default:
                        throw new Exception("Invalid packet");
                }
            } catch (Exception e){
                //TODO close socket
                e.printStackTrace();
            }
        }
        return null;
    }
}
