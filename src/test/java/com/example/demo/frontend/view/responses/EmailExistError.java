package com.example.demo.frontend.view.responses;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Getter
@Setter
public class EmailExistError extends JFrame {
    private JButton ok;
    public EmailExistError(){

        JLabel messageLabel = new JLabel();
        messageLabel.setText("Email Exist");
        this.add(messageLabel);

        setOk(new JButton());
        getOk().setText("ok");
        getOk().setFocusable(false);
        getOk().setSize(70,40);
        exitOut(getOk());
        this.add(getOk());


        this.setBounds(0, 50, 500, 50);
        this.setLayout(new FlowLayout());
        this.setTitle("Error Email Exist");
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
