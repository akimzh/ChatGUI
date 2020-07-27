package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGUI extends JFrame{

    public String nickName;

    public JoinGUI() {
        super("GUI Connect");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(c());
        setSize(300,120);
        setVisible(true);
    }

    private JPanel c() {
        JPanel contentPane = new JPanel(new BorderLayout(3,3));
        contentPane.add(joinPanel());
        return contentPane;
    }

    private JPanel joinPanel() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.Group hg1 = layout.createParallelGroup();
        GroupLayout.Group hg2 = layout.createParallelGroup();
        GroupLayout.Group hg3 = layout.createParallelGroup();
        JTextField nick = new JTextField(20);
        JLabel inputName = new JLabel("Введите Ваш ник");
        JButton button = new JButton("Подключиться к чату");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nickName = nick.getText();
                setVisible(false);
                new ChatGUI(nickName);
            }
        });
        hg1.addComponent(inputName);
        hg2.addComponent(nick);
        hg3.addComponent(button);
        GroupLayout.SequentialGroup hseq = layout.createSequentialGroup();
        hseq.addGroup(hg1);
        hseq.addGroup(hg2);
        hseq.addGroup(hg3);
        layout.setVerticalGroup(hseq);
        return panel;
    }

    public static void main(String[] args) {
        new JoinGUI();
    }
}
