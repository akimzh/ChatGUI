package client.gui;

import client.SocketClient;
import model.MessageEntity;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public interface Listener {
    public void performAction(String text);
}
