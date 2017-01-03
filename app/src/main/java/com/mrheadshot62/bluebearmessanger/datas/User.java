package com.mrheadshot62.bluebearmessanger.datas;

import java.io.Serializable;

/**
 * Created by novak on 27.12.2016.
 */

public class User implements Serializable {
    private int id;
    private String name;

    public User(String name) {
        this.name = name;
        this.id = (int)(Math.random()*5);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
