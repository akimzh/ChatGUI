package client.gui;

import client.SocketClient;
import model.MessageEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatGUI extends JFrame {
    private SocketClient socketClient;
    public JTextArea area;

    private JPanel b(String nickname) {
        JPanel contentPane = new JPanel(new BorderLayout(2,2));
        contentPane.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        contentPane.add(makePanel(nickname));
        socketClient = new SocketClient();
        socketClient.connect();
        return contentPane;
    }

    private void closeChat() {
        socketClient.disconnect();
    }

    public ChatGUI(String nickName){
        super("GUI client.Test");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(b(nickName));
        setSize(500,500);
        setVisible(true);
        if(socketClient!=null) {
            socketClient.getMessage(area);
        }
    }

    private JPanel makePanel(String nickname) {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.Group hg1 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        GroupLayout.Group hg2 = layout.createParallelGroup();
        GroupLayout.Group vg1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.Group vg2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.Group vg3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        area = new JTextArea(25,30);
        JScrollPane bar = new JScrollPane(area);
        bar.add(new JScrollBar());
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        JTextField textin = new JTextField(20);
        JButton button = new JButton("Send");
        JLabel lb = new JLabel(nickname);


        //ActionListener send = new TestActionListener();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.append(nickname+": "+textin.getText()+'\n');

                MessageEntity messageEntity = new MessageEntity(nickname, textin.getText(), System.currentTimeMillis());
                socketClient.sendMessage(messageEntity);
                // думал подключить где-то здесь
                textin.setText(null);
            }
        });

        hg1.addComponent(lb);
        hg1.addComponent(bar);
        vg1.addComponent(bar);
        vg1.addComponent(textin);
        vg1.addComponent(button);
        GroupLayout.SequentialGroup hseq = layout.createSequentialGroup();
        GroupLayout.SequentialGroup vseq = layout.createSequentialGroup();
        hseq.addGroup(hg1);
        vseq.addGroup(vg1);
        layout.setHorizontalGroup(vseq);
        layout.setVerticalGroup(hseq);
        //setContentPane(bar);
        //b.add(bar,);
        //b.setLayout(new FlowLayout());

        //b.add(text, FlowLayout.LEADING);
        //JButton button = new JButton("Send");

        //b.add(button, BorderLayout.);
        return panel;
    }

    /*
    public static class Form extends JPanel{
        public void paintComponent(Graphics g){

        }
    }*/

    public static void main(String[] args){
        new JoinGUI();
    }
}
