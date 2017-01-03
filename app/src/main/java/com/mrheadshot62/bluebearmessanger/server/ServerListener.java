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
    private Client c;
    private WifiInputStream wifiInputStream;

    private void sendAllExcept(Message mess, int id){
        System.out.printf("SendAllExcept %d%n", id);
        try {
            for (com.mrheadshot62.bluebearmessanger.server.Client c: com.mrheadshot62.bluebearmessanger.server.ServerController.serverStorage.getClients()) {
                if (c.getId() != id) {
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
        System.out.printf("Disconnecting #%d%n", c.getId());
        com.mrheadshot62.bluebearmessanger.server.ServerController.serverStorage.removeClient(c);
        cancel(false);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        ChatActivity.printMessage((Message)values[0]);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        while (c.getIn() != null) {
            try {
                Packet p = wifiInputStream.readPacket();
                System.out.println("Listener #" + c.getId() + " get packet");
                switch (p.getType()) {
                    case Datas.MESSAGE:
                        Message message = (Message) p.getData();
                        System.out.printf("#%d %s%n", c.getId(), message.getMessage());
                        sendAllExcept(message, c.getId());
                        publishProgress(message);
                        break;
                    case Datas.COMMAND:
                        Command command = (Command) p.getData();
                        System.out.printf("#%d %d%n", c.getId(), command.getCommand());
                        break;
                    default:
                        throw new Exception("Invalid packet");
                }
            } catch (Exception e) {
                //TODO close socket
                e.printStackTrace();//TODO create LOG
                disconnect();
                return null;
            }
        }
        return null;
    }

    public ServerListener(Client c){
        this.c = c;
        this.wifiInputStream = c.getIn();
    }
}
