package com.mrheadshot62.bluebearmessanger.client;


import android.util.Log;

import com.mrheadshot62.bluebearmessanger.datas.Message;
import com.mrheadshot62.bluebearmessanger.sockets.WifiInputStream;
import com.mrheadshot62.bluebearmessanger.sockets.WifiOutputStream;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by novak on 28.12.2016.
 */

class ClientThread extends Thread {
    public static int PORT = 5555;
    private final String ip;
    private Socket socket;
    private WifiOutputStream out;
    private WifiInputStream in;

    ClientThread(String ip){
        this.ip = ip;
        start();
    }

    void sendMessage(final Message mess) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    out.writeMessage(mess);
                    out.flush();
                    Log.d("SendMessage: ", mess.getMessage());
                }catch(Exception e){
                    Log.e("ClientThread", "sendMessage", e);
                }
            }
        }).start();
    }

    @Override
    public void run() {
        try{
            socket = new Socket(ip, PORT);
            out = new WifiOutputStream(socket.getOutputStream());
            in = new WifiInputStream(socket.getInputStream());
            ClientListener listener = new ClientListener(in);
            listener.execute();
            Log.i("Connected to server ", socket.getInetAddress().getHostAddress());
        }catch(Exception e){
            Log.e("Client Thread", e.getMessage(), e);
        }
    }
}
