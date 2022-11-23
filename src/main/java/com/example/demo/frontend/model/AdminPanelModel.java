package com.example.demo.frontend.model;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Management;
import com.example.demo.backend.repository.ManagementRepository;
import com.example.demo.frontend.controller.AdminPanelController;
import com.example.demo.frontend.view.AdminPanelView;
import com.example.demo.frontend.view.IndexView;

import javax.swing.*;
import java.util.List;

public class AdminPanelModel implements AdminPanelController {
    private final ManagementRepository managementRepository = new ManagementRepository();

    @Override
    public void updateProfile(AdminPanelView adminPanelView, JButton button) {
        try {
            button.addActionListener(e -> {
                Management management = adminPanelView.getManagement();

                management.setFirstName(adminPanelView.getFirstName().getText());
                management.setLastName(adminPanelView.getLastName().getText());
                management.setEmail(adminPanelView.getEmail().getText());
                management.setPassword(adminPanelView.getPassword().getText());

                management = managementRepository.updateManagementUser(management);
                new AdminPanelView(management);
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
    public void deleteAccount(AdminPanelView adminPanelView, JButton button) {
        button.addActionListener(e -> {
            managementRepository.deleteManagementUser(adminPanelView.getManagement().getId());
            adminPanelView.dispose();
            new IndexView();
        });
    }

    @Override
    public String[] listOfAdmins() {
        List<Management> managements = managementRepository.getAllManagement();
        String[] staff = new String[managements.size()];
        for(int i = 0; i<staff.length; i++) staff[i] = managements.get(i).getFirstName() + " " + managements.get(i).getLastName();
        return staff;
    }

    @Override
    public String[] listOfClients() {
        List<Client> clients = managementRepository.getAllCustomers();
        String[] staff = new String[clients.size()];
        for(int i = 0; i<staff.length; i++) staff[i] = clients.get(i).getFirstName() + " " + clients.get(i).getLastName();
        return staff;
    }
}
