package com.mrheadshot62.bluebearmessanger.server;



import android.util.Log;

import com.mrheadshot62.bluebearmessanger.datas.Command;
import com.mrheadshot62.bluebearmessanger.datas.Configuration;
import com.mrheadshot62.bluebearmessanger.datas.Message;
import com.mrheadshot62.bluebearmessanger.datas.Packet;
import com.mrheadshot62.bluebearmessanger.server.*;
import com.mrheadshot62.bluebearmessanger.server.ServerController;
import com.mrheadshot62.bluebearmessanger.server.ServerListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by novak on 27.12.2016.
 */

class ServerThread extends Thread {
    public static int PORT = 5555;
    private Configuration configuration;
    private ServerSocket serverSocket;


    public void stopServer(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPacket(Packet packet, int id){
        try {
            ServerController.serverStorage.getClient(id).getOut().writePacket(packet);
            ServerController.serverStorage.getClient(id).getOut().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message mess, int id){
        try {
            ServerController.serverStorage.getClient(id).getOut().writeMessage(mess);
            ServerController.serverStorage.getClient(id).getOut().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendCommand(Command command, int id){
        try {
            ServerController.serverStorage.getClient(id).getOut().writeCommand(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void startThread(Configuration configuration){
        this.configuration = configuration;
        start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(PORT);
            Log.d("ServerThread", "started!");
            Log.d("ServerThread", "name="+configuration.getName());
            Log.d("ServerThread", "pass="+configuration.getPassword());
            Log.d("ServerThread", "desc="+configuration.getDescription());
            while (serverSocket!=null){
                Socket socket = serverSocket.accept();
                com.mrheadshot62.bluebearmessanger.server.Client c = new com.mrheadshot62.bluebearmessanger.server.Client(socket);
//                if(!c.verify(configuration.getBlackList())){
//                    //TODO SEND MESSAGE
//                    socket.close();
//                    return;
//                }
                Log.d("ServerThread", "Connected "+socket.getInetAddress().getHostAddress());
                ServerController.serverStorage.addClient(c);
                ServerListener listener = new com.mrheadshot62.bluebearmessanger.server.ServerListener(c);
                listener.execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Configuration getConfiguration(){
        return configuration;
    }
}
