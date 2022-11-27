package com.example.demo.frontend.model;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Product;
import com.example.demo.backend.model.Requests;
import com.example.demo.backend.repository.ClientRepository;
import com.example.demo.backend.repository.ProductRepository;
import com.example.demo.backend.repository.RequestRepository;
import com.example.demo.frontend.controller.ClientPanelController;
import com.example.demo.frontend.view.responses.EmailExistError;
import com.example.demo.frontend.view.ClientPanelView;
import com.example.demo.frontend.view.IndexView;

import javax.persistence.RollbackException;
import javax.swing.*;
import java.util.List;

public class ClientPanelModel implements ClientPanelController {
    private final ClientRepository clientRepository = new ClientRepository();
    private final RequestRepository requestRepository = new RequestRepository();
    private final ProductRepository productRepository = new ProductRepository();

    @Override
    public void updateProfile(ClientPanelView clientPanelView, JButton button) {
        button.addActionListener(e -> {
            Client client = clientPanelView.getClient();

            client.setFirstName(clientPanelView.getFirstNameField().getText());
            client.setLastName(clientPanelView.getLastNameField().getText());
            client.setEmail(clientPanelView.getEmailField().getText());
            client.setPassword(clientPanelView.getPasswordField().getText());

            try {
                client = clientRepository.updateClient(client);
            } catch (RollbackException rbe){
                rbe.printStackTrace();
                new EmailExistError();
                return;
            }
            clientPanelView.dispose();
            new ClientPanelView(client);
        });
    }

    @Override
    public void logOut(ClientPanelView clientPanelView, JButton button) {
        button.addActionListener(e -> {
            clientPanelView.dispose();
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
    public void createRequest(ClientPanelView clientPanelView, JButton button, JComboBox<String> products) {
        button.addActionListener(e -> {
            Requests request = new Requests();
            request.setProduct(clientPanelView.getProductJComboBox().getSelectedItem().toString());

            for (int i = 0; i < clientPanelView.getQuantityField().getText().length(); i++) {
                char ch = clientPanelView.getQuantityField().getText().charAt(i);
                if (Character.isLetter(ch)) return;
            }
            request.setQuality(Integer.parseInt(clientPanelView.getQuantityField().getText()));
            requestRepository.createRequest(clientPanelView.getClient(), request);
            clientPanelView.dispose();
            new ClientPanelView(clientPanelView.getClient());
        });
    }

    @Override
    public List<Requests> allRequest(Client client) {
        return requestRepository.getAllRequest(client);
    }

    @Override
    public List<Product> allProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public void deleteRequest(ClientPanelView clientPanelView, JButton button, JComboBox<String> requestJCombo) {
        button.addActionListener(e -> {
            Requests request = new Requests();
            String temp = requestJCombo.getSelectedItem().toString();
            StringBuilder name = new StringBuilder();
            for (int i = 3; i < temp.length(); i++) {
                if (temp.charAt(i) == ' ') break;
                name.append(temp.charAt(i));
            }
            request.setId_request((long) Integer.parseInt(name.toString()));

            request = requestRepository.findRequest(request);
            requestRepository.deleteRequest(request.getId_request());
            clientPanelView.dispose();
            new ClientPanelView(clientPanelView.getClient());
        });
    }

}

