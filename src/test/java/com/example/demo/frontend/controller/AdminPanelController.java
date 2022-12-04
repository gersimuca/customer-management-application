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

    List<Client> allClients();

    List<Product> allProducts();

    List<Requests> allRequests();

    void createProduct(AdminPanelView adminPanelView, JButton button);

    void deleteRequest(AdminPanelView adminPanelView, JButton button, JComboBox requests);

    void approved(AdminPanelView adminPanelView, JComboBox jComboBox, JButton button);
    void deleteProduct(AdminPanelView adminPanelView, JButton button, JComboBox selectedProduct);

    void addProductQuantity(AdminPanelView adminPanelView, JComboBox selectedProduct, JButton button);
    void subtractProductQuantity(AdminPanelView adminPanelView, JComboBox selectedProduct, JButton button);
}
