package client.gui;

public class ButtonThread implements Runnable{
    public void run() {
        String s = "g";
        ChatGUI client = new ChatGUI(s);
    }
}