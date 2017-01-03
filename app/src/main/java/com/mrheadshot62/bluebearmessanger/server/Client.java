package com.mrheadshot62.bluebearmessanger.server;

import com.mrheadshot62.bluebearmessanger.datas.User;
import com.mrheadshot62.bluebearmessanger.server.ServerController;
import com.mrheadshot62.bluebearmessanger.sockets.WifiInputStream;
import com.mrheadshot62.bluebearmessanger.sockets.WifiOutputStream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by novak on 27.12.2016.
 */

class Client {
    private WifiInputStream in;
    private WifiOutputStream out;
    private int id;
    private User user;
    private InetAddress inetAddress;
    private Socket socket;

    public Client(Socket socket){
        try {
            this.socket = socket;
            in = new WifiInputStream(socket.getInputStream());
            out = new WifiOutputStream(socket.getOutputStream());
            inetAddress = socket.getInetAddress();
            this.id = ServerController.serverStorage.getClients().size();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean verify(User[] blackList){
        for (User user:blackList){
            if (user.getId() == id) return false;
        }
        return true;
    }

    public WifiInputStream getIn() {
        return in;
    }

    public WifiOutputStream getOut() {
        return out;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public Socket getSocket() {
        return socket;
    }
}
