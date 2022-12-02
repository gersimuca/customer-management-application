package com.example.demo.frontend.model;


import com.example.demo.backend.model.Management;
import com.example.demo.backend.repository.ManagementRepository;
import com.example.demo.frontend.controller.CreateAdminController;
import com.example.demo.frontend.view.CreateAdminView;
import com.example.demo.frontend.view.IndexView;
import com.example.demo.frontend.view.responses.EmailExistError;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.swing.*;

public class CreateAdminModel implements CreateAdminController {

    private final ManagementRepository managementRepository = new ManagementRepository();

    @Override
    public void createAdmin(CreateAdminView createAdminView, JButton button) {
        button.addActionListener(e -> {
            Management management = new Management();
            management.setFirstName(createAdminView.getFirstName().getText());
            management.setLastName(createAdminView.getLastName().getText());
            management.setEmail(createAdminView.getEmail().getText());
            management.setPassword(createAdminView.getPassword().getText());

            try {
                managementRepository.createManagementUser(management);
            } catch (PersistenceException pe){
                pe.printStackTrace();
                new EmailExistError();
                return;
            }
            createAdminView.dispose();
            new IndexView();
        });
    }

    @Override
    public void backToMain(JFrame frame, JButton button) {
        button.addActionListener(e -> {
            frame.dispose();
            new IndexView();
        });
    }
}
