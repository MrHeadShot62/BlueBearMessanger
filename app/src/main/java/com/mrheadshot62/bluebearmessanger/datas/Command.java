package com.mrheadshot62.bluebearmessanger.datas;

import java.io.Serializable;

/**
 * Created by novak on 27.12.2016.
 */

public class Command implements Serializable {
    private int command;
    private Object[] arguments;

    public Command(int command, Object... arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public int getCommand() {
        return command;
    }

    public Object[] getArguments() {
        return arguments;
    }
}
