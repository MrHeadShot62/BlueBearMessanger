package com.mrheadshot62.bluebearmessanger.interfaces;

/**
 * Created by novak on 26.12.2016.
 */

public interface InterfaceServerActivity {
    void friendsOnly();
    void startServer();
    void setServerName(String serverName);
    void setBlacklist(String[] names); //TODO create User class
    void setPassword(String password);
    void setDescription(String description);

}
