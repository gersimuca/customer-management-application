package com.example.demo.frontend.model;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Product;
import com.example.demo.backend.model.Requests;
import com.example.demo.backend.repository.ClientRepository;
import com.example.demo.backend.repository.RequestRepository;
import com.example.demo.frontend.controller.ClientPanelController;
import com.example.demo.frontend.view.ClientPanelView;
import com.example.demo.frontend.view.IndexView;

import javax.swing.*;
import java.util.List;

public class ClientPanelModel implements ClientPanelController {
    private final ClientRepository clientRepository = new ClientRepository();
    private final RequestRepository requestRepository = new RequestRepository();

    @Override
    public void updateProfile(ClientPanelView clientPanelView, JButton button) {
        try {
            button.addActionListener(e -> {
                Client client = clientPanelView.getClient();

                client.setFirstName(clientPanelView.getFirstName().getText());
                client.setLastName(clientPanelView.getLastName().getText());
                client.setEmail(clientPanelView.getEmail().getText());
                client.setPassword(clientPanelView.getPassword().getText());

                client = clientRepository.updateClient(client);
                new ClientPanelView(client);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public void createRequest(ClientPanelView clientPanelView, JButton button) {
        button.addActionListener(e -> {
            Requests request = new Requests();
            request.setProduct(clientPanelView.getProductName().getText());
            request.setQuality(Integer.parseInt(clientPanelView.getQuantity().getText()));

            Client client = clientPanelView.getClient();

            System.out.println(request.getProduct());
            System.out.println(request.getQuality());

            requestRepository.createRequest(client, request);
            clientPanelView.dispose();
            new ClientPanelView(client);
        });
    }

    @Override
    public List<Requests> allRequest(Client client) {
        return requestRepository.getAllRequest(client);
    }

    @Override
    public String[] listOfRequests(List<Requests> requests) {
        String[] request = new String[requests.size()];
        for (int i = 0; i<requests.size(); i++) request[i] = requests.get(i).getProduct() + " [" + requests.get(i).getStatus();
        return request;
    }

    @Override
    public Requests findRequest(List<Requests> requests, JComboBox jComboBox) {
        Requests request = new Requests();
        for(Requests sample : requests){
            StringBuilder s = new StringBuilder(sample.getProduct() + " [" + sample.getStatus());
            if(s.toString().equals(jComboBox.getSelectedItem())){
                request = sample;
                break;
            }
        }
        return request;
    }

    @Override
    public void deleteRequest(ClientPanelView clientPanelView, JButton button, Requests requests) {
        button.addActionListener(e -> {
            requestRepository.deleteRequest(requests.getId_request());
            Client client = clientPanelView.getClient();
            clientPanelView.dispose();
            new ClientPanelView(client);
        });
    }
}
