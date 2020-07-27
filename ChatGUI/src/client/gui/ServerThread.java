/*

package client.gui;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {

    //String text = null;
    Socket socket1 = null;
    Socket socket2 = null;
    Thread t;
    //HashMap <String, Socket> hshmp = null;

    public ServerThread(Socket socket1,Socket socket2) {
        //this.hshmp = hshmp;
        this.socket1 = socket1;
        this.socket2 = socket2;
        t = new Thread();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            rd.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        t.start();
        //String s = "123";
        //hshmp.put(s,socket);
    }

    public void run(){
        new JoinGUI();
        try {
            BufferedReader ra = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
            ra.readLine();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
*/