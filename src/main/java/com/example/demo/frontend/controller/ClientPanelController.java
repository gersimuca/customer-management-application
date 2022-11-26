package com.example.demo.frontend.controller;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Product;
import com.example.demo.backend.model.Requests;
import com.example.demo.frontend.view.ClientPanelView;

import javax.swing.*;
import java.util.List;

public interface ClientPanelController {

    void updateProfile(ClientPanelView clientPanelView, JButton button);
    void logOut(JFrame frame, JButton button);
    void deleteAccount(ClientPanelView clientPanelView, JButton button);
    void createRequest(ClientPanelView clientPanelView, JButton button);

    List<Requests> allRequest(Client client);
    String[] listOfRequests(List<Requests> requests);
    Requests findRequest(List<Requests> requests, JComboBox jComboBox);
    void deleteRequest(ClientPanelView clientPanelView, JButton button, Requests requests);


}
