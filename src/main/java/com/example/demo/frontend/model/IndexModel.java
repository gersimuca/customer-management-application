package com.example.demo.frontend.model;

import com.example.demo.frontend.controller.IndexController;
import com.example.demo.frontend.view.CreateClientView;

import javax.swing.*;

public class IndexModel implements IndexController {
    @Override
    public void createStaff(JButton button) {

    }

    @Override
    public void createClient(JFrame frame, JButton button) {
        button.addActionListener(e -> {
            frame.dispose();
            new CreateClientView();
        });
    }

    @Override
    public void loginClient(JButton button) {

    }

    @Override
    public void loginStaff(JButton button) {

    }
}
