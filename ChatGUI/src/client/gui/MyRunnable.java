package client.gui;

import com.sun.tools.doclets.internal.toolkit.util.DocFinder;
import model.MessageEntity;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyRunnable extends Thread {

    private List<Socket> clientList;

    public MyRunnable(List<Socket> clientList) {
        this.clientList = clientList;
        //this.clientList.add(socket);
    }

    public void run() {
        Socket socket = clientList.get(clientList.size()-1);
        if (socket.isConnected()) {
            while (true) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    MessageEntity mes = (MessageEntity) ois.readObject();
                    System.out.println("Received msg=" + mes.toString());

                    for (Socket s : clientList) {
                        if (s != socket && s.isConnected()) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ObjectOutputStream oos = new ObjectOutputStream(baos);
                            oos.writeObject(mes);
                            oos.flush();
                            oos.close();
                            OutputStream os = s.getOutputStream();
                            os.write(baos.toByteArray());
                            os.flush();
                        }
                    }
                    //TODO: implement sending message to other users
                    //MessageEntity messageEntity = new MessageEntity(mes.getUserName(),mes.getMessage(),mes.getDateTime());
                    //sendMessage(messageEntity);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException cl) {
                    cl.printStackTrace();
                }
            }
        }
    }
}
    /*public void sendMessage(MessageEntity messageEntity) {
        System.out.println("sendMessage="+messageEntity);
        if (socket != null && socket.isConnected()) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(messageEntity);
                oos.flush();
                oos.close();
                OutputStream os = socket.getOutputStream();
                os.write(baos.toByteArray());
                os.flush();
                System.out.println("Message sent.");
            }  catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }*/
