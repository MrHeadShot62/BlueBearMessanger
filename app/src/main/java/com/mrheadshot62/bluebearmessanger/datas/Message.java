package com.mrheadshot62.bluebearmessanger.datas;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by novak on 27.12.2016.
 */

public class Message implements Serializable {
    private String message;
    private User author;
    private Date date;
    private int id;

    public Message(String message, User author) {
        this.message = message;
        this.author = author;
        date = new Date();
    }

    public String getMessage() {
        return message;
    }

    public User getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
