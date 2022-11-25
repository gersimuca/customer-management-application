package com.example.demo.frontend.controller;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Management;
import com.example.demo.backend.model.Product;
import com.example.demo.backend.model.Requests;
import com.example.demo.frontend.view.AdminPanelView;

import javax.swing.*;
import java.util.List;

public interface AdminPanelController {
    void updateProfile(AdminPanelView adminPanelView, JButton button);
    void logOut(JFrame frame, JButton button);
    void deleteAccount(AdminPanelView adminPanelView, JButton button);

    List<Management> listOfManagers();
    String[] listOfAdmins(List<Management> managements);

    List<Client> allClients();
    String[] listOfClients(List<Client> clients);

    List<Product> allProducts();
    String[] listOfProducts(List<Product> products);

    List<Requests> allRequests();
    String[] listOfRequest(List<Requests> requests);

    void createProduct(AdminPanelView adminPanelView, JButton button);

    void deleteRequest(AdminPanelView adminPanelView, JButton button, Requests requests);
    Requests findRequest(List<Requests> requests, JComboBox jComboBox);

    void approved(AdminPanelView adminPanelView, Requests requests, JButton button, List<Product> product);
    Product findProduct(List<Product> products, JComboBox jComboBox);
    void deleteProduct(AdminPanelView adminPanelView, JButton button, Product product);


}
