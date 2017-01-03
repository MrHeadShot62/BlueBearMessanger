package com.mrheadshot62.bluebearmessanger.sockets;

import android.support.annotation.NonNull;

import com.mrheadshot62.bluebearmessanger.datas.Command;
import com.mrheadshot62.bluebearmessanger.datas.Datas;
import com.mrheadshot62.bluebearmessanger.datas.Message;
import com.mrheadshot62.bluebearmessanger.datas.Packet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by novak on 27.12.2016.
 */

public class WifiOutputStream extends ObjectOutputStream {
    public WifiOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    public void writePacket(@NonNull Packet p) throws IOException {
        writeObject(p);
        flush();
    }

    public void writeMessage(@NonNull Message mess) throws IOException {
        writeObject(new Packet(mess, Datas.MESSAGE));
    }

    public void writeCommand(@NonNull Command command) throws IOException{
        writeObject(new Packet(command, Datas.COMMAND));
    }


}
