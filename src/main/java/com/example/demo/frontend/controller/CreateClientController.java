package com.example.demo.frontend.controller;

import com.example.demo.frontend.view.CreateClientView;

import javax.swing.*;

public interface CreateClientController {
    void createClient(CreateClientView createClientView, JButton button);
    void backToMain(JFrame frame, JButton button);
}
