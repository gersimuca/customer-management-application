package com.example.demo.frontend.view.responses;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class QuantityCanNotBeNegativeError extends JFrame{
    private JButton ok;
    public QuantityCanNotBeNegativeError(){

        JLabel messageLabel = new JLabel();
        messageLabel.setText("Quantity Can Not BeNegative");
        this.add(messageLabel);

        setOk(new JButton());
        getOk().setText("ok");
        getOk().setFocusable(false);
        getOk().setSize(70,40);
        exitOut(getOk());
        this.add(getOk());


        this.setBounds(0, 50, 500, 50);
        this.setLayout(new FlowLayout());
        this.setTitle("Quantity Can Not BeNegative");
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
