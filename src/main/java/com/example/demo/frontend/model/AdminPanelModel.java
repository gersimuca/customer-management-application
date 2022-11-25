package com.example.demo.frontend.model;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Management;
import com.example.demo.backend.model.Product;
import com.example.demo.backend.repository.ManagementRepository;
import com.example.demo.backend.repository.ProductRepository;
import com.example.demo.frontend.controller.AdminPanelController;
import com.example.demo.frontend.view.AdminPanelView;
import com.example.demo.frontend.view.IndexView;

import javax.swing.*;
import java.util.List;

public class AdminPanelModel implements AdminPanelController {
    private final ManagementRepository managementRepository = new ManagementRepository();
    private final ProductRepository productRepository = new ProductRepository();
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
    public List<Management> listOfManagers() {
        return managementRepository.getAllManagement();
    }

    @Override
    public String[] listOfAdmins(List<Management> managements) {
        String[] staff = new String[managements.size()];
        for (int i = 0; i<managements.size(); i++) staff[i] = managements.get(i).getFirstName() + " " + managements.get(i).getLastName();
        return staff;
    }

    @Override
    public List<Client> allClients() {
        return managementRepository.getAllCustomers();
    }

    @Override
    public String[] listOfClients(List<Client> clients) {
        String[] staff = new String[clients.size()];
        for (int i = 0; i<clients.size(); i++) staff[i] = clients.get(i).getFirstName() + " " + clients.get(i).getLastName();
        return staff;
    }

    @Override
    public List<Product> allProducts() {
        return managementRepository.getAllProducts();
    }

    @Override
    public String[] listOfProducts(List<Product> products) {
        String[] staff = new String[products.size()];
        for (int i = 0; i<products.size(); i++) staff[i] = products.get(i).getProductName();
        return staff;
    }

    @Override
    public void createProduct(AdminPanelView adminPanelView, JButton button) {
        button.addActionListener(e -> {
            Product product = new Product();
            Management management = adminPanelView.getManagement();

            product.setProductName(adminPanelView.getProductName().getText());
            product.setManufacturer(adminPanelView.getManufacture().getText());
            product.setCountryOfOrigin(adminPanelView.getCountry().getText());
            product.setQuantity(adminPanelView.getQuantity().getText());

            productRepository.createProduct(product);
            adminPanelView.dispose();
            new AdminPanelView(management);
        });
    }


}
