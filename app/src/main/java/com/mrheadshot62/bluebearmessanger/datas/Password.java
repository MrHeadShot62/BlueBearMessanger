package com.mrheadshot62.bluebearmessanger.datas;

import java.io.Serializable;

/**
 * Created by novak on 03.01.2017.
 */

public class Password implements Serializable {
    private String pass;

    public Password(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }
}
