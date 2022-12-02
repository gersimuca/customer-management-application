package com.example.demo.frontend.model;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Management;
import com.example.demo.backend.repository.ClientRepository;
import com.example.demo.backend.repository.ManagementRepository;
import com.example.demo.frontend.controller.IndexController;
import com.example.demo.frontend.view.*;
import com.example.demo.frontend.view.responses.IncorrectLoginCredentialsError;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

public class IndexModel implements IndexController {
    @Override
    public void createStaff(JFrame frame, JButton button) {
        button.addActionListener(e -> {
            frame.dispose();
            new CreateAdminView();
        });
    }

    @Override
    public void createClient(JFrame frame, JButton button) {
        button.addActionListener(e -> {
            frame.dispose();
            new CreateClientView();
        });
    }

    @Override
    public void loginClient(IndexView indexView, JButton button) {

        button.addActionListener(e -> {
            Client client;
            try {
                client = new ClientRepository().login(indexView.getTextFieldClientEmail().getText(), indexView.getTextFieldClientPassword().getText());
                if (client.getEmail().equals(indexView.getTextFieldClientEmail().getText())) {
                    if (client.getPassword().equals(indexView.getTextFieldClientPassword().getText())) {
                        indexView.dispose();
                        new ClientPanelView(client);
                    }
                }
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                new IncorrectLoginCredentialsError();
            }
        });

    }

    @Override
    public void loginStaff(IndexView indexView, JButton button) {

        button.addActionListener(e -> {
            try {
                Management management = new ManagementRepository().login(indexView.getTextStaffEmail().getText(), indexView.getTextStaffPassword().getText());
                if (management.getEmail().equals(indexView.getTextStaffEmail().getText())) {
                    if (management.getPassword().equals(indexView.getTextStaffPassword().getText())) {
                        indexView.dispose();
                        new AdminPanelView(management);
                    }
                }
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                new IncorrectLoginCredentialsError();
            }
        });
    }
}
