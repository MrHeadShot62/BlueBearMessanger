package com.mrheadshot62.bluebearmessanger.server;

import android.os.AsyncTask;

import com.mrheadshot62.bluebearmessanger.activities.ChatActivity;
import com.mrheadshot62.bluebearmessanger.datas.Command;
import com.mrheadshot62.bluebearmessanger.datas.Datas;
import com.mrheadshot62.bluebearmessanger.datas.Message;
import com.mrheadshot62.bluebearmessanger.datas.Packet;
import com.mrheadshot62.bluebearmessanger.sockets.WifiInputStream;

import java.io.EOFException;
import java.io.IOException;


/**
 * Created by novak on 27.12.2016.
 */

class ServerListener extends AsyncTask {
    private Client client;
    private WifiInputStream wifiInputStream;

    private void sendAllExcept(Message mess){
        System.out.printf("SendAllExcept %d%n", client.getId());
        try {
            for(Client c:ServerController.serverStorage.getClients()) {
                if (c.getId() != client.getId()) {
                    c.getOut().writeMessage(mess);
                    c.getOut().flush();
                }
            }
        } catch (EOFException e) {
            e.printStackTrace();
        }catch(IOException exx) {
            System.out.println("IOException");
        }
    }

    private void disconnect(){
        System.out.printf("Disconnecting #%d%n", client.getId());
        ServerController.serverStorage.removeClient(client);
        cancel(false);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        ChatActivity.printMessage((Message)values[0]);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        while (client.getIn() != null) {
            try {
                Packet p = wifiInputStream.readPacket();
                switch (p.getType()) {
                    case Datas.MESSAGE:
                        Message message = (Message) p.getData();
                        System.out.printf("#%d %s%n", client.getId(), message.getMessage());
                        sendAllExcept(message);
                        publishProgress(message);
                        break;
                    case Datas.COMMAND:
                        Command command = (Command) p.getData();
                        System.out.printf("#%d %d%n", client.getId(), command.getCommand());
                        break;
                    default:
                        throw new Exception("Invalid packet");
                }
            } catch (Exception e) {
                e.printStackTrace();//TODO create LOG
                disconnect();
                return null;
            }
        }
        disconnect();
        return null;
    }

    ServerListener(Client client){
        this.client = client;
        this.wifiInputStream = client.getIn();
    }
}
