package com.mrheadshot62.bluebearmessanger.client;

import com.mrheadshot62.bluebearmessanger.datas.Message;

import java.io.IOException;

/**
 * Created by novak on 28.12.2016.
 */

public class ClientController {
    private ClientThread clientThread;

    public void startThread(String ip){
        clientThread = new ClientThread(ip);
    }

    public void sendMessage(Message mess){
        try {
            clientThread.sendMessage(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
