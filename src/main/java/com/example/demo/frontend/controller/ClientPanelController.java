package com.example.demo.frontend.controller;

import com.example.demo.frontend.view.ClientPanelView;

import javax.swing.*;

public interface ClientPanelController {

    void updateProfile(ClientPanelView clientPanelView, JButton button);
    void logOut(JFrame frame, JButton button);
    void deleteAccount(ClientPanelView clientPanelView, JButton button);

}
