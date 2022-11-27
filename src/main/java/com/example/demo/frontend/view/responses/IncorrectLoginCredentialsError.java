package com.example.demo.frontend.view.responses;

import javax.swing.*;
import java.awt.*;

public class IncorrectLoginCredentialsError extends JFrame{
    private JButton ok;
    public IncorrectLoginCredentialsError(){

        JLabel messageLabel = new JLabel();
        messageLabel.setText("Incorrect Login Credentials");
        this.add(messageLabel);

        ok = new JButton();
        ok.setText("ok");
        ok.setFocusable(false);
        ok.setSize(70,40);
        exitOut(ok);
        this.add(ok);


        this.setBounds(0, 50, 500, 50);
        this.setLayout(new FlowLayout());
        this.setTitle("Incorrect Login Credentials");
        this.setSize(500, 100);
        this.setResizable(true);
        this.setVisible(true);
    }

    void exitOut(JButton button){
        button.addActionListener(e -> {
            this.dispose();
        });
    }
}
