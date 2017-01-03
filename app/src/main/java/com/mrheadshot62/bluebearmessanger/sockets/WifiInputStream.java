package com.mrheadshot62.bluebearmessanger.sockets;

import com.mrheadshot62.bluebearmessanger.datas.Command;
import com.mrheadshot62.bluebearmessanger.datas.Datas;
import com.mrheadshot62.bluebearmessanger.datas.Message;
import com.mrheadshot62.bluebearmessanger.datas.Packet;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class WifiInputStream extends ObjectInputStream {
    public WifiInputStream(InputStream in) throws IOException {
        super(in);
    }


    public Packet readPacket() throws IOException{
        try {
            Object obj = readObject();
            if (obj instanceof Packet){
                return (Packet)obj;
            }else{
                throw new ClassNotFoundException("Not a packet!");
            }
        }catch(ClassNotFoundException e){
            return readPacket();
        }
    }

    public Message readMessage() throws IOException{
        Packet p = readPacket();
        if (p.getType() != Datas.MESSAGE) throw new IOException("Invalid received packet");
        return (Message)p.getData();
    }
    public Command readCommand() throws IOException{
        Packet p = readPacket();
        if (p.getType() != Datas.COMMAND) throw new IOException("Invalid received packet");
        return (Command) p.getData();
    }
}
