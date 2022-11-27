package com.example.demo.frontend.controller;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Product;
import com.example.demo.backend.model.Requests;
import com.example.demo.frontend.view.ClientPanelView;

import javax.swing.*;
import java.util.List;

public interface ClientPanelController {

    void updateProfile(ClientPanelView clientPanelView, JButton button);
    void logOut(ClientPanelView clientPanelView, JButton button);
    void deleteAccount(ClientPanelView clientPanelView, JButton button);
    void createRequest(ClientPanelView clientPanelView, JButton button, JComboBox<String> products);

    List<Requests> allRequest(Client client);
    List<Product> allProducts();
    void deleteRequest(ClientPanelView clientPanelView, JButton button, JComboBox<String> requests);

}
