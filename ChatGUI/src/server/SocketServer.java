package server;

import client.gui.JoinGUI;
import client.gui.MyRunnable;
import model.MessageEntity;
import utils.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;



public class SocketServer {

    private List <Socket> clientList;

    public void startServer() throws IOException {
        ServerSocket server = new ServerSocket(Constants.SERVER_PORT);
        clientList = new ArrayList<>();
        //int i = 0;
        try {
            while(true) {
                Socket socket = server.accept();
                clientList.add(socket);
                //System.out.println(clientList.size());
                //System.out.println(clientList.size());
                //Thread sc = new MyRunnable(clientList.get(i));
                Thread sc = new MyRunnable(clientList);
                sc.start();
                //i++;
            }
        } finally {
            server.close();
        }
    }

    public static void main(String[] args) {
        try {
            SocketServer socketServer = new SocketServer();
            socketServer.startServer();
        } catch (IOException ex) {
            System.out.println("Can't connect to server");
            ex.printStackTrace();
        }
    }
}