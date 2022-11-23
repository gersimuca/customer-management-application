package com.example.demo.frontend.model;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.repository.ClientRepository;
import com.example.demo.frontend.controller.ClientPanelController;
import com.example.demo.frontend.view.ClientPanelView;
import com.example.demo.frontend.view.IndexView;

import javax.swing.*;

public class ClientPanelModel implements ClientPanelController {
    private final ClientRepository clientRepository = new ClientRepository();

    @Override
    public void updateProfile(ClientPanelView clientPanelView, JButton button) {
        button.addActionListener(e -> {
            Client client = clientPanelView.getClient();

            client.setFirstName(clientPanelView.getFirstName().getText());
            client.setLastName(clientPanelView.getLastName().getText());
            client.setEmail(clientPanelView.getEmail().getText());
            client.setPassword(clientPanelView.getPassword().getText());

            client = clientRepository.updateClient(client);
            new ClientPanelView(client);
        });
    }

    @Override
    public void logOut(JFrame frame, JButton button) {
        button.addActionListener(e -> {
            frame.dispose();
            new IndexView();
        });
    }

    @Override
    public void deleteAccount(ClientPanelView clientPanelView, JButton button) {
        button.addActionListener(e -> {
            clientRepository.deleted(clientPanelView.getClient().getId_client());
            clientPanelView.dispose();
            new IndexView();
        });
    }
}
