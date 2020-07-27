package client.gui;

import client.SocketClient;
import model.MessageEntity;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MyClientRunnable extends Thread {
    private Socket socket;
    private JTextArea area;

    public MyClientRunnable(Socket socket, JTextArea area) {
        this.socket = socket;
        this.area = area;
    }

    public void run() {
        while (!socket.isClosed()) {
            try {
                System.out.println(socket + "; isConnected=" + socket.isConnected() + "; isBound=" + socket.isBound() +
                 "; isClosed=" + socket.isClosed() + "; isInputShutdown=" + socket.isInputShutdown() + "; isOutputShutdown=" +
                socket.isOutputShutdown());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                MessageEntity mes = (MessageEntity) ois.readObject();//читаем объект
                System.out.println(mes.toString());//выводим его на экран
                area.append(mes.getUserName()+": "+mes.getMessage()+'\n');
            } catch(Exception e){
                try{
                    socket.close();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
