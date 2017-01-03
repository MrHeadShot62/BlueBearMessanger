package com.mrheadshot62.bluebearmessanger.datas;

/**
 * Created by novak on 27.12.2016.
 */

public class Configuration {
    private String name;
    private String password;
    private boolean friendsOnly;
    private String description;
    private User[] blackList;


    public void setPassword(String password) {
        this.password = password;
    }

    public void setFriendsOnly(boolean friendsOnly) {
        this.friendsOnly = friendsOnly;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBlackList(User[] blackList) {
        this.blackList = blackList;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isFriendsOnly() {
        return friendsOnly;
    }

    public String getDescription() {
        return description;
    }

    public User[] getBlackList() {
        return blackList;
    }

    public void setName(String name) {
        this.name = name;
    }
}
