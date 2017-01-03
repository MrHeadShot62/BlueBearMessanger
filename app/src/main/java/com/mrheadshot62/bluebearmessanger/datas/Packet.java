package com.mrheadshot62.bluebearmessanger.datas;

import java.io.Serializable;

/**
 * Created by novak on 27.12.2016.
 */

public class Packet implements Serializable {
    private Object data;
    private int type;

    public Packet(Object data, int type) {
        this.data = data;
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public int getType() {
        return type;
    }
}
