package com.mrheadshot62.bluebearmessanger.server;


import android.util.Log;

import com.mrheadshot62.bluebearmessanger.datas.Configuration;
import com.mrheadshot62.bluebearmessanger.datas.Datas;
import com.mrheadshot62.bluebearmessanger.datas.Message;
import com.mrheadshot62.bluebearmessanger.datas.Packet;

/**
 * Created by novak on 27.12.2016.
 */

public class ServerController {
    private ServerThread serverThread;
    static ServerStorage serverStorage;
    private boolean isStarted=false;

    public ServerController(){
        ServerController.serverStorage = new ServerStorage();
        this.serverThread = new ServerThread();
    }

    public void startServer(Configuration configuration){
        if (!isStarted) {
            serverThread.startThread(configuration);
            isStarted = true;
        }
    }

    public void sendMessage(Message mess){
        Log.d("ServerController", String.format("%s: %s", mess.getAuthor().getName(), mess.getMessage()));
        for (Client c:serverStorage.getClients()) {
            serverThread.sendMessage(mess, c.getId());
        }
    }

    public void sendRaw(Packet p){
        for (Client c:serverStorage.getClients()){
            sendRaw(p, c.getId());
        }
    }

    public void stopServer(){
        if (isStarted) {
            serverThread.stopServer();
            isStarted = false;
        }
    }

    private void sendRaw(Packet p, int id){
        if (isStarted) serverThread.sendPacket(p, id);
    }

    public Message getMessage(int id){
        return serverStorage.getMessage(id);
    }

    public Message[] getLastMessages(int count){
        return serverStorage.getLastMessages(count);
    }

    public Configuration getInfo(){
        return serverThread.getConfiguration();
    }

    public String getStatus(){ //TODO
        if (isStarted){
            return "Started";
        }else {
            return "Not Started";
        }
    }
}
