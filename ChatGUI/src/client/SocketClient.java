package client;

import client.gui.Listener;
import client.gui.MyClientRunnable;
import model.MessageEntity;
import utils.Constants;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class SocketClient {
    private Socket socket;

    private MessageEntity messageEntity = null;
    private ObjectInputStream ois = null;

    public boolean connect() {
        try {
            socket = new Socket(InetAddress.getByName(Constants.SERVER_HOST), Constants.SERVER_PORT);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();

            return false;
        }
    }

    public void sendMessage(MessageEntity messageEntity) {
        try{
            System.out.println("sendMessage="+messageEntity);
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

    public void getMessage(JTextArea area) {
            //MessageEntity mes = (MessageEntity) ois.readObject();//читаем объект
            //System.out.println(mes.toString());
            //Берем объект из сокета подключенного к серверу

            Thread mcr = new MyClientRunnable(socket,area);//отправляем в отдельный поток
            mcr.start();//запускаем поток
            System.out.println("SocketClient.getMessage");
    }

    public void disconnect() {
        try {
            if (socket != null && socket.isConnected()) {
                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        SocketClient socketClient = new SocketClient();
        if (socketClient.connect()) {
            SocketClient s = new SocketClient();
            //MessageEntity messageEntity = new MessageEntity("testUserName", "cool message!", System.currentTimeMillis());
            //socketClient.sendMessage(messageEntity);

        } else {
            socketClient.disconnect();
        }

        /* Scanner scan = new Scanner(System.in);
        while (true) {
            String str = scan.nextLine();
            try {
                byte[] word = str.getBytes();
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
                System.out.println(word.toString());
                out.write(str);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        while(true) {
            MyRunnable thread = new MyRunnable(s);
            thread.run();
            System.out.println("inside socket client");
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                out.flush();
            } catch (NullPointerException ex){
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        //BufferedWriter output = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        //output.write();

        //PrintWriter output = new PrintWriter(s.getOutputStream());
        //System.out.println("Введите свой никнэйм:\n");
        //output.write("Akim");
        //output.flush();
    }
}
