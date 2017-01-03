package com.mrheadshot62.bluebearmessanger.server;

import android.support.annotation.Nullable;

import com.mrheadshot62.bluebearmessanger.datas.Message;
import com.mrheadshot62.bluebearmessanger.datas.User;

import java.util.ArrayList;

/**
 * Created by novak on 27.12.2016.
 */

class ServerStorage {
    private ArrayList<com.mrheadshot62.bluebearmessanger.server.Client> clients = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();

    public void addClient( com.mrheadshot62.bluebearmessanger.server.Client c){
        clients.add(c);
    }

    public void removeClient(com.mrheadshot62.bluebearmessanger.server.Client c){
        clients.remove(c);
    }

    public void removeClient(int id){
        for (com.mrheadshot62.bluebearmessanger.server.Client c:clients){
            if (c.getId() == id) clients.remove(c);
        }
    }

    public ArrayList<com.mrheadshot62.bluebearmessanger.server.Client> getClients(){
        return clients;
    }

    @Nullable
    public com.mrheadshot62.bluebearmessanger.server.Client getClient(User user){
        for (com.mrheadshot62.bluebearmessanger.server.Client c:clients){
            if (c.getUser()==user) return c;
        }
        return null;
    }

    @Nullable
    public com.mrheadshot62.bluebearmessanger.server.Client getClient(int id){
        for (com.mrheadshot62.bluebearmessanger.server.Client c:clients){
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void addMessage(Message message){
        message.setId(messages.size());
        messages.add(message);
    }

    @Nullable
    public Message getMessage(int id){
        for (Message message:messages){
            if (message.getId() == id) return message;
        }
        return null;
    }

    public Message[] getLastMessages(int count){
        ArrayList<Message> messages1 = new ArrayList<>();
        for (int i=messages.size()-count; i<=messages.size();i++){
            messages1.add(messages.get(i));
        }
        return (Message[]) messages1.toArray();
    }
}
