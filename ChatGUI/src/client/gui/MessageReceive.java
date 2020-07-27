package client.gui;

import java.io.Serializable;

public class MessageReceive implements Serializable {

    private String userName;
    private String message;
    private long dateTime;

    public MessageReceive(String userName, String message, long dateTime) {
        this.userName = userName;
        this.message = message;
        this.dateTime = dateTime;
    }
}
